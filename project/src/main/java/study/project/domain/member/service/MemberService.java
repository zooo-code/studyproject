package study.project.domain.member.service;

import study.project.domain.member.Member;

import java.util.Optional;

public interface MemberService {
    Member join(Member member);

    Optional<Member> findByIdMember(Long memberId);

    Member deleteMember(Member member);
    Long countMember();
}
