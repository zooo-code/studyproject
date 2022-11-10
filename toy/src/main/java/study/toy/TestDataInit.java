package study.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.toy.domain.item.Item;
import study.toy.domain.item.MemoryItemRepository;
import study.toy.domain.member.Member;
import study.toy.domain.member.MemoryMemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemoryItemRepository memoryItemRepository;
    private final MemoryMemberRepository memoryMemberRepository;
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        memoryItemRepository.save(new Item("itemA", 10000, 10));
        memoryItemRepository.save(new Item("itemB", 20000, 20));

        memoryMemberRepository.save(new Member("test","test","test1"));
    }

}