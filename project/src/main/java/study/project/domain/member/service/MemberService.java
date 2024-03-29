package study.project.domain.member.service;

import study.project.domain.address.Address;
import study.project.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member join(Member member);

    Optional<Member> findByIdMember(Long memberId);

    void deleteMember(Member member);
    Long countMember();
    Member edit(Long id, String name, String password, Address address);

    Boolean findByLoginId(String LoginId);

    List<Member> findMembers();

    Boolean checkOrderAndItem(Long memberId);



}
