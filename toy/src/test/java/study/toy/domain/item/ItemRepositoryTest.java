package study.toy.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.toy.domain.item.repository.MemoryItemRepository;


class ItemRepositoryTest {


    MemoryItemRepository itemRepository = new MemoryItemRepository();

    @AfterEach
    void clearStore(){
        itemRepository.clearStore();
    }
    @Test
    void save(){
        //given
        Item item = new Item("test1", 10000, 1);


        //when
        itemRepository.save(item);
        //then

    }
}