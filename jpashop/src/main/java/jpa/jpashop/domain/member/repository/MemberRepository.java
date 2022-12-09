package jpa.jpashop.domain.member.repository;

import jpa.jpashop.domain.member.Member;

public interface MemberRepository {

    Long save(Member member);

    Member findById(Long id);



}
