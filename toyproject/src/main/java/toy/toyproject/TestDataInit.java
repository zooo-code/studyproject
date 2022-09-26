package toy.toyproject;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import toy.toyproject.domain.item.Item;
import toy.toyproject.domain.item.MemoryItemRepository;
import toy.toyproject.domain.member.Member;
import toy.toyproject.domain.member.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {


    private final MemberRepository memberRepository;
    private final MemoryItemRepository memoryItemRepository;
    /**
     * 테스트용 데이터 추가
     * @PostConstruct 스프링이 시작할때 처음 메모리에 올려줌
     */
    @PostConstruct
    public void init() {

        memoryItemRepository.save(new Item("itemA",10000,"서울","넓은 집"));
        memoryItemRepository.save(new Item("itemB",10000,"경기도","1인 숙소"));
        Member member = new Member();
        member.setEmail("test@naver.com");
        member.setPassword("1234");
        member.setName("테스터");




        memberRepository.save(member);

    }

}