package jpa.jpashop.domain.member.repository;

import jpa.jpashop.domain.member.Member;

import java.util.List;

public interface MemberRepository {

    Long save(Member member);

    Member findOne(Long id);

    List<Member> findAll();
    List<Member> findByName(String name);

    List<Member> findByLoginId(String loginId);
}
