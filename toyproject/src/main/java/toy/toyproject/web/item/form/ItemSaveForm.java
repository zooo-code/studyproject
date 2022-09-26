package toy.toyproject.web.item.form;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//아이템 저장에 필요한 데티어 리스트를 작성하자
@Data
public class ItemSaveForm {

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 5000, max = 10000000)
    private Integer price;

//    @NotNull
//    @Max(value = 9999)
//    private Integer quantity;

    @NotBlank
    private String Where;

    @NotBlank
    private String Explain;



}
