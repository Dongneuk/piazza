package teamJCI.sprout.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String text;
    private LocalDateTime uploadDate;

    @Enumerated(EnumType.STRING)
    private VisibleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setUser(User user) {
        this.user = user;
        user.getContents().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getContents().add(this);
    }


}
