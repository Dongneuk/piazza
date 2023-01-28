package teamJCI.sprout.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String password;
    private String username;

    @OneToMany(mappedBy = "user")
    private List<Content> contents = new ArrayList<>();
}
