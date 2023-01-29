package teamJCI.sprout.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teamJCI.sprout.domain.Content;
import teamJCI.sprout.domain.User;
import teamJCI.sprout.domain.reply.Comment;
import teamJCI.sprout.repository.ContentRepository;
import teamJCI.sprout.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @Autowired
    private final ContentRepository contentRepository;

    @PostMapping("/content/{contentId}/comment")
    public void commentWrite(@PathVariable Long contentId, @RequestBody Comment comment, User user) {

        commentService.commentWrite(contentId, comment, user);
    }

//    @PostMapping("/comment_delete")
//    private String commentDelete(@ModelAttribute Comment comment) {
//
//        return CommentService.commentDelete(comment);
//    }
}
