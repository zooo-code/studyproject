package study.toy.domain.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    public void save(Member member){
        member.setId(sequence++);
        store.put(member.getId(),member);
    }


    public Member findByUserId(Long id){
        return store.get(id);
    }
    public void clearStore(){
        store.clear();
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }
}
