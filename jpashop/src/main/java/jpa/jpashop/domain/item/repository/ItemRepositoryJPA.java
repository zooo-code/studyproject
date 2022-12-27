package jpa.jpashop.domain.item.repository;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.web.item.form.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;



@Repository
@RequiredArgsConstructor
public class ItemRepositoryJPA implements ItemRepository{

    private final EntityManager em;

    @Override
    public void save(Item item) {
        if (item.getId() == null){
            em.persist(item);
        } else{
            em.merge(item);
        }
    }

    @Override
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
    @Override
    public void update(Long id, ItemForm itemForm){
        Item item = em.find(Item.class, id);
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setStockQuantity(itemForm.getStockQuantity());
    }

//    @Override
//    public Item deleteItem(Long id) {
//        Item item = em.find(Item.class, id);
//        em.remove(item);
//        return item;
//    }
}
