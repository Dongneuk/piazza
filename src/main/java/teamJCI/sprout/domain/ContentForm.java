package teamJCI.sprout.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentForm {

    @NotNull()
    private String title;

    private String text;

    private String status;

    private Long userId;

    private Long categoryId;
}
