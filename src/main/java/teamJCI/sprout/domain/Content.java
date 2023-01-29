package teamJCI.sprout.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import teamJCI.sprout.domain.reply.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Content {

    @Id
    @GeneratedValue
    @Column(name = "content_id")
    private Long id;

    private String text;
    private String title;
    private LocalDateTime uploadDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private VisibleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "content")
    private List<Comment> comments;



    public void setUser(User user) {
        this.user = user;
        user.getContents().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getContents().add(this);
    }


}
