package study.project.domain.item.category;


import lombok.Getter;
import lombok.Setter;
import study.project.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class ItemCategory {

    @Id @GeneratedValue
    @Column(name = "item_category_id")
    private Long id;

    @OneToMany(mappedBy = "itemCategory")
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> items = new ArrayList<>();


//  양방향 연관관계 매서드
    public void addItem(Item item){
        items.add(item);
        item.setItemCategory(this);
    }

    public void addCategory(Category category){
        categories.add(category);
        category.setItemCategory(this);
    }
}
