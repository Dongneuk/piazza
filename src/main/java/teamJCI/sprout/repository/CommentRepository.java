package teamJCI.sprout.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamJCI.sprout.domain.reply.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
