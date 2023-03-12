package study.project.web.order.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderForm {

    private String sellerId;
    private Long itemId;
    private String itemName;
    private Integer price;
    @NotNull(message = "필수 입력")
    @Min(value = 1,message = "최소 1개 이상 구매해야 합니다.")
    private Integer buyQuantity;

    public OrderForm(String sellerId, Long itemId, String itemName, Integer price, Integer buyQuantity) {
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.buyQuantity = buyQuantity;
    }
}
