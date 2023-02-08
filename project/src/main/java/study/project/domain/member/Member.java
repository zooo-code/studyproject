package study.project.domain.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(of = {"id", "username", "loginId","password"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String loginId;
    private String password;

    public Member(String username, String loginId, String password) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
    }
}
