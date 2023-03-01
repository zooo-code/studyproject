package study.project.web.member.form;

import lombok.Getter;
import lombok.Setter;
import study.project.domain.address.Address;

import javax.validation.constraints.NotEmpty;
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
