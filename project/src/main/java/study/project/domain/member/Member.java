package study.project.domain.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.project.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ToString(of = {"id", "username", "loginId","password"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY)
    private List<Item> items =new ArrayList<>();
    private String username;
    private String loginId;
    private String password;
    private LocalDateTime createMemberTime;
    public Member(String username, String loginId, String password) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.createMemberTime = LocalDateTime.now();
    }

    //==양방향 연관관계 메서드==//

    public void addItem(Item item){
        items.add(item);
        item.setMember(this);
    }
}
