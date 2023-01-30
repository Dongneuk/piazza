package teamJCI.sprout.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import teamJCI.sprout.domain.reply.Comment;
import teamJCI.sprout.domain.reply.CommentResponse;
import teamJCI.sprout.repository.CommentRepository;
import teamJCI.sprout.repository.ContentRepository;
import teamJCI.sprout.repository.UserRepository;
import teamJCI.sprout.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @Autowired
    private final ContentRepository contentRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CommentRepository commentRepository;

    @GetMapping("/content")
    public void contentStatus() {
        System.out.println("content : GOOD");
    }

    @PostMapping("/content/comment")
    public void commentWrite(@RequestBody CommentResponse commentResponse) {
//        System.out.println(commentResponse.getUserId() + " " + commentResponse.getContentId());
        Comment comment = new Comment();

        comment.setUser(userRepository.findById(commentResponse.getUserId()));
        comment.setContent(contentRepository.findById(commentResponse.getContentId()));
        comment.setReply(commentResponse.getReply());
        commentRepository.save(comment);
//        commentService.commentWrite(contentId, comment, user);
    }

//    @PostMapping("/comment_delete")
//    private String commentDelete(@ModelAttribute Comment comment) {
//
//        return CommentService.commentDelete(comment);
//    }

}
