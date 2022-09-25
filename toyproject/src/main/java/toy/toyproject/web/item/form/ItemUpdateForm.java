package toy.toyproject.web.item.form;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateForm {
    @NotNull
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 5000, max = 10000000)
    private Integer price;

    //수정에서는 수량은 자유롭게 변경할 수 있다.
//    private Integer quantity;

    @NotEmpty
    private String Where;

    @NotEmpty
    private String Explain;
}
