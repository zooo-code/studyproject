package study.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.toy.domain.item.Item;
import study.toy.domain.item.MemoryItemRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemoryItemRepository memoryItemRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        memoryItemRepository.save(new Item("itemA", 10000, 10));
        memoryItemRepository.save(new Item("itemB", 20000, 20));
    }

}