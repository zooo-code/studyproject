package study.project.api.member.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateMemberRequest {
    @NotNull
    private String username;
    @NotNull
    private String loginId;
    @NotNull
    private String password;
}
