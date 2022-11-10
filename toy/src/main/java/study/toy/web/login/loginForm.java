package study.toy.web.login;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class loginForm {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
