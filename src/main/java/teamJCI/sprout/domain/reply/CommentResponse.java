package teamJCI.sprout.domain.reply;

import lombok.Data;

@Data
public class CommentResponse {
    private Long userId;
    private Long contentId;

    private String reply;
}
