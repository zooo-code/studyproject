package study.toy.domain.member.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import study.toy.domain.member.Member;
import study.toy.domain.member.repository.MemberRepository;

import java.util.Optional;

@Slf4j
public class MemberServiceTransaction {

    private final MemberRepository memberRepository;

    public MemberServiceTransaction(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) {
        bizLogic(fromId,toId,money);
    }


    private void bizLogic(String fromId, String toId, int money)  {
        Optional<Member> fromMember = memberRepository.findByLoginId(fromId);
        Optional<Member> toMember = memberRepository.findByLoginId(toId);

        memberRepository.update(fromId, fromMember.get().getMoney()-money);
        validation(toMember.get());
        memberRepository.update(toId, toMember.get().getMoney() + money);

    }
    private void validation(Member toMember) {
        if (toMember.getLoginId().equals("ex")) {
            log.info("error 이체중 예외 발생= {}",toMember.getLoginId());
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
