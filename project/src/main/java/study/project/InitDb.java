package study.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.address.Address;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.domain.order.service.OrderService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();

    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final MemberService memberService;
        private final ItemService itemService;
        private final OrderService orderService;
        public void dbInit1(){
            Address address = new Address("123", "서울", null, " (서울 아파트, 서울)");
            Member member = new Member("kim", "test", "123",address);
            Member memberA = memberService.join(member);
            Address address1 = new Address("123", "서울 ", null, " (서울 아파트, 서울)");
            Member member2 = new Member("lee", "test12", "123",address1);
            Member memberB = memberService.join(member2);
            Address address3 = new Address("123", "서울", null, " (서울 아파트, 서울)");
            Member member3 = new Member("young", "test1", "123",address3);
            Member join = memberService.join(member3);
            for (int i = 1; i <=30 ; i++){
                Item item = new Item(memberA, "kimItem" + i, 100 + i, 1000 * i);
                Item item1 = itemService.saveItem(item);
                orderService.order(memberB.getId(),item1.getId(),i+1);
                orderService.order(member3.getId(),item1.getId(),i+1);

            }
            for (int i =1; i<=1; i++){
                Item item2 = new Item(memberB, "leeItem" + i, i + 10, 100 * i);
                Item item3 = itemService.saveItem(item2);
                orderService.order(memberA.getId(),item3.getId(),i+1);
            }
            for (int i = 1; i <=1 ; i++){
                Item item = new Item(join, "young" + i, 100 + i, 1000 * i);
                Item item1 = itemService.saveItem(item);
                orderService.order(memberB.getId(),item1.getId(),i+1);
            }

        }
        public void dbInit2(){




        }
    }
}
