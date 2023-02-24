package study.project.web.item.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class MemberItemDto {

    private String loginId;
    private Long itemId;
    private String itemName;
    private int stockQuantity;
    private String createItemTime;
    private int price;

    @QueryProjection
    public MemberItemDto(String loginId, Long itemId, String itemName, int stockQuantity, String createItemTime, int price) {
        this.loginId = loginId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.createItemTime = createItemTime;
        this.price = price;
    }
}
