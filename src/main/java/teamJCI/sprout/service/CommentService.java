package teamJCI.sprout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamJCI.sprout.domain.Content;
import teamJCI.sprout.domain.User;
import teamJCI.sprout.domain.reply.Comment;
import teamJCI.sprout.repository.CommentRepository;
import teamJCI.sprout.repository.ContentRepository;
import teamJCI.sprout.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final ContentRepository contentRepository;

    @Autowired
    private final UserRepository userRepository;


    public void commentWrite(Long contentId, Comment comment, User user) {

        Content contentById = contentRepository.findById(contentId);

        comment.setContent(contentById);
        comment.setUser(user);

        commentRepository.save(comment);

    }
}
