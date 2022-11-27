package study.toy.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;
    public Item() {
    }
    public Item(String name, Integer price, Integer quantity) {
        this.itemName = name;
        this.price = price;
        this.quantity = quantity;
    }
}
