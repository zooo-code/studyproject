package study.project.web.member.form;

import lombok.Getter;
import lombok.Setter;
import study.project.domain.address.Address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateMemberForm {
    private Long Id;
    private String loginId;
    private LocalDateTime createTime;
    @NotEmpty(message = "입력 필수")
    private String username;

    @NotEmpty(message = "입력 필수")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotEmpty(message = "필수 사항입니다.")
    private String zipCode;
    @NotEmpty(message = "필수 사항입니다.")
    private String address;

    private String detailAddress;

    private String etc;


    public UpdateMemberForm(Long id, String loginId,
                            String username, String password, String zipCode, String address, String detailAddress, String etc) {
        Id = id;
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.zipCode = zipCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.etc = etc;
    }
}
