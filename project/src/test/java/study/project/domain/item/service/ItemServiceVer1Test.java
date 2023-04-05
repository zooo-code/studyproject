package study.project.domain.item.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.repository.ItemRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceVer1Test {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testTime () {
        //given
        List<Item> all = itemRepository.findAll();
        //when
        for (Item item : all) {
            System.out.println("item = " + item.getCreateItemTime());
        }
        //then
    }
}