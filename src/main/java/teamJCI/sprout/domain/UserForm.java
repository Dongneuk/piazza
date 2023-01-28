package teamJCI.sprout.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
