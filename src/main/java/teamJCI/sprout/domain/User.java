package teamJCI.sprout.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import teamJCI.sprout.domain.reply.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String password;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
