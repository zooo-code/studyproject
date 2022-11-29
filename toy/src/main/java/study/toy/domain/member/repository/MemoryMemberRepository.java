package study.toy.domain.member.repository;

import lombok.extern.slf4j.Slf4j;
import study.toy.domain.member.Member;

import java.util.*;
@Slf4j
//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    public Member save(Member member){
        member.setId(sequence++);
        log.info("save: member={}", member);
        store.put(member.getId(),member);
        return member;
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

    @Override
    public void delete(String login_id) {

    }

    @Override
    public void update(String login_id, int money) {

    }

    public void clearStore(){
        store.clear();
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
}
