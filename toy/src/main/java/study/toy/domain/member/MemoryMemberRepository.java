package study.toy.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
@Slf4j
@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    public void save(Member member){
        member.setId(sequence++);
        log.info("save: member={}", member);
        store.put(member.getId(),member);
    }


    public Member findById(Long id){
        return store.get(id);
    }
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clearStore(){
        store.clear();
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
}
