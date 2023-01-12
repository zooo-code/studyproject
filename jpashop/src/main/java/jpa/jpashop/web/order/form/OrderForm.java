package jpa.jpashop.web.order.form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class OrderForm {

    private Long id;


    private Long memberId;

    private Long itemId;

    @NotEmpty
    private String itemName;

    @NotNull
    private int itemPrice;


    @NotNull
    @Min(value = 1)
    private int count;
}
