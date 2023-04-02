package study.project.web.item.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Getter
public class MemberItemDto {

    private String loginId;
    private Long itemId;
    private String itemName;
    private int stockQuantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createItemTime;
    private int price;

    @QueryProjection
    public MemberItemDto(String loginId, Long itemId, String itemName, int stockQuantity, LocalDateTime createItemTime, int price) {
        this.loginId = loginId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.createItemTime = createItemTime;
        this.price = price;
    }
}
