package study.project.web.member.form;


import lombok.Getter;
import lombok.Setter;
import study.project.domain.address.Address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberForm {
    @NotEmpty(message = "아이디는 필수 입니다.")
    @Pattern(regexp = "(?=.*[0-9]+)[a-zA-Z][a-zA-Z0-9]{5,10}",
            message = "영문자로 시작하고, 5~10 길이의 영문자와 숫자의 조합이어야 합니다.")
    private String loginId;

    @NotEmpty(message = "비밀 번호는 필수 입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
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
