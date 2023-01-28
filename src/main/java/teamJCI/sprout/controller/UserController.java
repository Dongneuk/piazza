package teamJCI.sprout.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import teamJCI.sprout.domain.User;
import teamJCI.sprout.domain.UserForm;
import teamJCI.sprout.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/signup")
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/createUserForm";
    }

    // @Valid와 BindingResult를 통해 userForm에서 에러발생(빈칸제출) 시, 가입을 진행시키지 않음
    @PostMapping("/user/signup")
    public String create(@Validated UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return "user/createUserForm";
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());

        userService.join(user);
        return "redirect:/";
    }


    @GetMapping("/user/userList")
    public String users(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "user/userList";
    }

}
