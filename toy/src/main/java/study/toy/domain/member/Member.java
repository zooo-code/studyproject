package study.toy.domain.member;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;

    private String name;

    private String loginId;

    private String password;

    public Member(String name, String loginid, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}
