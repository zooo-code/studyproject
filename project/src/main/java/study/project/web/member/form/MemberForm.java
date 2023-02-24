package study.project.web.member.form;


import lombok.Getter;
import lombok.Setter;
import study.project.domain.address.Address;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "아이디는 필수 입니다.")
    private String loginId;
    @NotEmpty(message = "비밀 번호는 필수 입니다.")
    private String password;
    @NotEmpty(message = "이름은 필수 입니다.")
    private String username;
    @NotEmpty(message = "필수 사항입니다.")
    private String zipCode;
    @NotEmpty(message = "필수 사항입니다.")
    private String address;

    private String detailAddress;

    private String etc;

}
