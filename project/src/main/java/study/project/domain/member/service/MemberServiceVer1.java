package study.project.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.address.Address;

import study.project.domain.item.repository.ItemRepository;
import study.project.domain.member.Member;
import study.project.domain.member.repository.MemberRepository;
import study.project.domain.order.repository.OrderRepository;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceVer1 implements MemberService{

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    @Override
    @Transactional
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByIdMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public Long countMember() {
        return memberRepository.count();
    }

    @Transactional
    @Override
    public Member edit(Long id, String name, String password, Address address) {
        Member member = memberRepository.findById(id).get();
        member.update(name,password,address);
        return member;
    }

    @Override
    public Boolean findByLoginId(String LoginId) {
        Optional<Member> byLoginId = memberRepository.findByLoginId(LoginId);
        return byLoginId.isPresent();
    }

    @Override
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Boolean checkOrderAndItem(Long memberId) {
        int sizeItems = itemRepository.myItemList(memberId).size();
        int sizeOrders = orderRepository.myOrderList(memberId).size();
        return sizeItems != 0 && sizeOrders != 0;
    }

}
