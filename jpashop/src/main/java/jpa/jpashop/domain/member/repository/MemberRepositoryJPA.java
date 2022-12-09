package jpa.jpashop.domain.member.repository;


import jpa.jpashop.domain.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepositoryJPA implements MemberRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    @Override
    public Member findById(Long id){
        return em.find(Member.class, id);
    }
}
