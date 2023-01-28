package teamJCI.sprout.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teamJCI.sprout.domain.*;
import teamJCI.sprout.service.CategoryService;
import teamJCI.sprout.service.ContentService;
import teamJCI.sprout.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final UserService userService;
    private final CategoryService categoryService;

    // 글 작성 부분, users와 categories 를 불러온 이유는 글 작성시 작성자와 게시판을 선택해야하기 때문이다.
    @GetMapping("/content/write")
    public String createForm(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("contentForm", new ContentForm());
        return "content/createContentForm";
    }

    // 글 작성 부분을 눌렀을 때, content 등록 부분인데, form에 들어온 입력들을 통해 content를 생성하고 등록한다.
    @PostMapping("/content/write")
    public String create(@Validated ContentForm contentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "content/createContentForm";
        }

        Content content = new Content();

        if (contentForm.getStatus().equals("visible")) {
            content.setStatus(VisibleStatus.VISIBLE);
        } else {
            content.setStatus(VisibleStatus.HIDDEN);
        }

        content.setTitle(contentForm.getTitle());
        content.setText(contentForm.getText());
        content.setUser(userService.findUser(contentForm.getUserId()));
        content.setCategory(categoryService.findCategory(contentForm.getCategoryId()));
        content.setUploadDate(LocalDateTime.now());

        contentService.register(content);

        return "redirect:/";
    }

    // categoryId(cn)를 통해 해당 contents들을 불러옴. name은 화면 위에 표시되는 게시판 이름
    @GetMapping("/content/contentListC/{cn}")
    public String cContents(@PathVariable("cn") Long cn, Model model) {
        List<Content> contents = contentService.findListC(cn);
        String name = contentService.findNameC(cn) + "글보기";
        model.addAttribute("name", name);
        model.addAttribute("contents", contents);
        return "content/contentList";
    }

    // userId(un) 를 통해 해당 contents들을 불러옴. name은 화면 위에 표시되는 게시판 이름
    @GetMapping("/content/contentListU/{un}")
    public String uContents(@PathVariable("un") Long un, Model model) {
        List<Content> contents = contentService.findListU(un);
        String name = contentService.findNameU(un) + "의 글보기";
        model.addAttribute("name", name);
        model.addAttribute("contents", contents);
        return "content/contentList";
    }

    // 글 보기 버튼 클릭시 해당 contents의 Id를 가져와서, 이 id에 맞는 content를 불러옴.
    @GetMapping("/content/edit/{contentId}")
    public String getContent(@PathVariable("contentId") Long contentId, Model model) {
        Content content = contentService.findById(contentId);
        model.addAttribute("content", content);
        return "content/edit";
    }

    // 글 수정 버튼 클릭시 content 수정 부분인데, contentForm이 아닌 @RequestParam으로 값을 하나씩 받아오고,
    // @PathVariable 로 contentId 까지 받아옴. 비밀번호가 일치하면, contentId에 해당하는
    // content의 text와 VisibleStatus 를 update 시켜줌.
    @PostMapping("/content/edit/{contentId}")
    public String edit(@RequestParam("status") String status,
                       @RequestParam("text") String text,
                       @RequestParam("password") String password,
                       @PathVariable("contentId") Long contentId) {

        if (contentService.checkPassword(contentId, password)) {
            if (status.equals("visible")) {
                contentService.update(contentId, text, VisibleStatus.VISIBLE);
            } else {
                contentService.update(contentId, text, VisibleStatus.HIDDEN);
            }

            return "redirect:/";
        } else {
            return "redirect:/content/edit/{contentId}";
        }
    }

}
