package jpa.jpashop.domain.member.service;

import jpa.jpashop.domain.member.Member;

import java.util.List;

public interface MemberService {

    Long join(Member member);

    void validateDuplicateMember(Member member);

    List<Member> findMembers();

    Member findOne(Long memberId);

    void update(Long id, String name);
}
