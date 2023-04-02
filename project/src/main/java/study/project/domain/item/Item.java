package study.project.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;
import study.project.domain.exception.NotEnoughStockException;
import study.project.domain.file.UploadFile;
import study.project.domain.item.category.ItemCategory;
import study.project.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String itemName;
    private Integer stockQuantity;
    private Integer price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createItemTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_category_id")
    private ItemCategory itemCategory;

    @OneToOne(fetch = LAZY ,cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadFile_id")
    private UploadFile imageFile;



    public void setMember(Member member) {
        this.member = member;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Item(Member member, String itemName, Integer stockQuantity, Integer price, UploadFile imageFile) {
        this.member = member;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.createItemTime = LocalDateTime.now();
        this.imageFile = imageFile;
    }

    public Item(Member member, String itemName, Integer stockQuantity, Integer price) {
        this.member = member;
        this.itemName = itemName;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.createItemTime = LocalDateTime.now();

    }

    //비지니스 로직
    public void addStock(Integer quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(Integer quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

    public void update(String name, Integer price, Integer stockQuantity,UploadFile imageFile) {
        this.itemName = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageFile =imageFile;
    }
}
