package study.project.web.item.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemForm {


    private Long userId;
    private Long itemId;

    @NotEmpty
    private String name;
    @NotNull
    @Min(value = 10, message = "최소 100원 이상이어야 합니다.")
    private int price;
    @NotNull
    @Min(value = 1, message = "최소 1개 이상이어야 합니다.")
    private int stockQuantity;


}
