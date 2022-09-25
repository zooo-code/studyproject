package toy.toyproject.domain.item;


import lombok.Data;

@Data
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private String Where;
    private String Explain;
    public Item(){

    }

    public Item(String itemName, Integer price, String where, String explain) {
        this.itemName = itemName;
        this.price = price;
        this.Where = where;
        this.Explain = explain;
    }
}
