package study.project.api.member.response;

import lombok.Data;

@Data
public class UpdateMemberResponse {
    private Long id;
    private String username;
    private String password;


    public UpdateMemberResponse(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
