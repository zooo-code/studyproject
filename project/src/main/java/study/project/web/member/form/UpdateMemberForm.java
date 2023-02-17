package study.project.web.member.form;

import lombok.Getter;
import lombok.Setter;

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

    public UpdateMemberForm(Long id, String loginId, String username, String password) {
        Id = id;
        this.loginId = loginId;
        this.username = username;
        this.password = password;
    }
}
