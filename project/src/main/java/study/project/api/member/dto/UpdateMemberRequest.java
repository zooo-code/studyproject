package study.project.api.member.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateMemberRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
