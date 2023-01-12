package jpa.jpashop.web.member.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;
    @NotEmpty(message = "비밀 번호는 필수 입니다.")
    private String password;

    @NotEmpty(message = "주소는 필수 입니다.")
    private String city;
    @NotEmpty(message = "주소는 필수 입니다.")
    private String street;
    @NotEmpty(message = "주소는 필수 입니다.")
    private String zipcode;
}
