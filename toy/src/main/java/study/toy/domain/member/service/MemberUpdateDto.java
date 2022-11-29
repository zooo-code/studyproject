package study.toy.domain.member.service;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberUpdateDto {
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    public MemberUpdateDto() {
    }

    public MemberUpdateDto(String password, String name) {
        this.password = password;
        this.name = name;
    }
}
