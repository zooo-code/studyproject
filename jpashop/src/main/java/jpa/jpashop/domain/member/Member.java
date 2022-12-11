package jpa.jpashop.domain.member;

import jpa.jpashop.domain.delivery.Address;
import jpa.jpashop.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String loginId;

    private String password;

    @Embedded
    private Address address;
//    연관관계의 주인은 외래 키가 있는 곳에 있다. 따라서 member를 타고 들어가면
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}
