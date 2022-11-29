package study.toy.domain.item.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import study.toy.domain.item.Item;
import study.toy.domain.item.service.ItemUpdateDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static study.toy.domain.item.QItem.item;

@Repository
@Transactional
public class JpaItemRepositoryQuerydsl implements ItemRepository{


    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepositoryQuerydsl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }
    @Override
    public void deleteItem(Long id){
        Item item = em.find(Item.class, id);
        em.remove(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();
        List<Item> result = query
                .select(item)
                .from(item)
                .where(likeItemName(itemName), maxPrice(maxPrice))
                .fetch();
        return result;
    }
    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }
}
