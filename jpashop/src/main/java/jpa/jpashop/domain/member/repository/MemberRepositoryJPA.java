package jpa.jpashop.domain.member.repository;


import jpa.jpashop.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MemberRepositoryJPA implements MemberRepository{

    private final EntityManager em;
    @Override
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    @Override
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
    @Override
    public List<Member> findByLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId",Member.class)
                .setParameter("loginId",loginId)
                .getResultList();
    }

    @Override
    public Member findByLoginIdForLogin(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId",loginId)
                .getSingleResult();
    }


}
