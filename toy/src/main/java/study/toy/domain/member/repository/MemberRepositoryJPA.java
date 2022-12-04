package study.toy.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import study.toy.domain.member.Member;
import study.toy.domain.member.service.MemberUpdateDto;

import javax.persistence.EntityManager;
import java.util.Optional;


@Slf4j
//@Repository
public class MemberRepositoryJPA implements MemberRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public MemberRepositoryJPA(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long id) {

        return null;
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {

        Member member = em.find(Member.class, loginId);
        return Optional.ofNullable(member);
    }

    @Override
    public void delete(String login_id) {
        Member member = em.find(Member.class, login_id);
        em.remove(member);
    }

    @Override
    public void update(String login_id, int money) {

    }

    public void updateUserData(String login_id, MemberUpdateDto updateParam){
        Optional<Member> findByLoginId = findByLoginId(login_id);
        findByLoginId.get().setPassword(updateParam.getPassword());
        findByLoginId.get().setName(updateParam.getName());
    }

}
