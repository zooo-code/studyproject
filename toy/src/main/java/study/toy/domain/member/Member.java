package study.toy.domain.member;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;

    private String name;

    private String userid;

    private String password;

    public Member(String name, String userid, String password) {
        this.name = name;
        this.userid = userid;
        this.password = password;
    }
}