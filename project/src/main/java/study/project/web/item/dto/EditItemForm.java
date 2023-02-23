package study.project.web.item.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EditItemForm {
    private Long id;
    @NotEmpty(message = "입력 필수")
    private String itemName;
    @NotNull(message = "입력 필수 입니다.")
    @Min(value = 10, message = "최소 100원 이상이어야 합니다.")
    @Max(value = 5000000, message = "5,000,000원 이상은 거래가 불가합니다.")
    private int price;
    @NotNull(message = "입력 필수")
    @Min(value = 1, message = "최소 1개 이상이어야 합니다.")
    private int stockQuantity;

}
