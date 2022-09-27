package toy.toyproject.domain.item;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Item {

    @Id
    private Long id;

    private String itemName;
    private Integer price;
    private String where;
    private String explain;

    public Item(){
    }

    public Item(String itemName, Integer price, String where, String explain) {
        this.itemName = itemName;
        this.price = price;
        this.where = where;
        this.explain = explain;
    }
}
