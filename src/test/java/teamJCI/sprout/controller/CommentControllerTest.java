package teamJCI.sprout.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import teamJCI.sprout.domain.User;
import teamJCI.sprout.domain.reply.Comment;
import teamJCI.sprout.repository.CommentRepository;
import teamJCI.sprout.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentControllerTest {

    CommentRepository commentRepository;

    UserRepository userRepository;
    @Test
    public void save() {

        Comment comment = new Comment();

        User user1 = new User();
        userRepository.save(user1);

        comment.setUser(user1);
        commentRepository.save(comment);

        User findUserById = userRepository.findById(user1.getId());
        Assertions.assertEquals(user1, findUserById);

    }

}