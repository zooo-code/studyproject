package jpa.jpashop.domain.item.itemcategory;


import jpa.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Setter @Getter
public class Book extends Item {
    private String author;
    private String isbn;
}
