package study.project.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;


    private String itemName;
    private int stockQuantity;

    private int price;

    public Item(String itemName, int stockQuantity, int price) {
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }
}
