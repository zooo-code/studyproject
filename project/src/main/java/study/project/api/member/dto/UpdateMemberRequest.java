package study.project.api.member.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateMemberRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotEmpty(message = "필수 사항입니다.")
    private String zipCode;
    @NotEmpty(message = "필수 사항입니다.")
    private String address;

    private String detailAddress;

    private String etc;
}
