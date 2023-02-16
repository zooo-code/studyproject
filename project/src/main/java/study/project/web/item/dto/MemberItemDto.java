package study.project.web.item.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;


@Getter
public class MemberItemDto {

    private String loginId;
    private Long itemId;
    private String itemName;

    private int stockQuantity;
    private int price;

    @QueryProjection
    public MemberItemDto(String loginId, Long itemId, String itemName, int stockQuantity, int price) {
        this.loginId = loginId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }
}
