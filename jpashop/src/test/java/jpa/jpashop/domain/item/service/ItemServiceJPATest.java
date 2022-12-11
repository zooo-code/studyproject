package jpa.jpashop.domain.item.service;

import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.itemcategory.Book;
import jpa.jpashop.domain.item.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@Transactional
@SpringBootTest
class ItemServiceJPATest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 아이템_저장_찾기(){
        Book book = new Book();
        book.setName("소설");
        itemService.saveItem(book);

        Item findBook = itemRepository.findOne(book.getId());
        Assertions.assertEquals(book,findBook);

        assertThat(book).isEqualTo(findBook);

    }


}