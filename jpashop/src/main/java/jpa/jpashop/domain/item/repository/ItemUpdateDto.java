package jpa.jpashop.domain.item.repository;


import javax.validation.constraints.NotEmpty;

public class ItemUpdateDto {

    @NotEmpty
    private String name;

    private int price;

    private int stockQuantity;
}
