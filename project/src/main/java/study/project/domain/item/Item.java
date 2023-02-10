package study.project.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.project.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String itemName;
    private int stockQuantity;

    private LocalDateTime createItemTime;
    private int price;

    public Item(Member member, String itemName, int stockQuantity,  int price) {
        this.member = member;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.createItemTime = LocalDateTime.now();
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
