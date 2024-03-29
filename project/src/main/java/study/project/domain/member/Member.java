package study.project.domain.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.project.domain.address.Address;
import study.project.domain.item.Item;
import study.project.domain.order.Order;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Item> items =new ArrayList<>();
    private String username;
    private String loginId;
    private String password;
    private LocalDateTime createMemberTime;

    @Enumerated(EnumType.STRING)
    private Grade grade;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


    public Member(String username, String loginId, String password , Address address) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.createMemberTime = LocalDateTime.now();
        this.address = address;
    }

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

    //비지니스 매서드
    public void update(String username, String password, Address address){
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
