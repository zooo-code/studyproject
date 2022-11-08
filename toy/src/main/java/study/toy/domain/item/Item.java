package study.toy.domain.item;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private int quantity;

    public Item(String name, Integer price, int quantity) {
        this.itemName = name;
        this.price = price;
        this.quantity = quantity;
    }
}
