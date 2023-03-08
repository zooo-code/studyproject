package study.project.domain.item.category;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ItemCategory {

    @Id @GeneratedValue
    @Column(name = "item_category_id")
    private Long id;



}
