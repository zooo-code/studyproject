package jpa.jpashop.web.login;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "아이디를 확인 해주세요")
    private String loginId;

    @NotEmpty(message = "비밀 번호를 확인 해주세요.")
    private String password;
}
