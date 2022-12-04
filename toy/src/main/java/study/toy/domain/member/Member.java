package study.toy.domain.member;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Column(name = "LOGIN_ID")
    private String loginId;
    @NotEmpty
    private String password;

    private int money;

    public Member() {
    }

    public Member(Long id, String name, String loginId, String password) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }

    public Member(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;

    }

    public Member(Long id, String name, String loginId, String password, int money) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.money = money;
    }
}
