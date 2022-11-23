package study.toy.domain.config;


import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.toy.domain.item.repository.ItemRepository;
import study.toy.domain.item.repository.JdbcTemplateItemRepository;
import study.toy.domain.item.service.ItemService;
import study.toy.domain.item.service.ItemServiceV1;

import javax.sql.DataSource;


//@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {

    private final DataSource dataSource;

    @Bean
    public ItemService itemService(){
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JdbcTemplateItemRepository(dataSource);
    }

}
