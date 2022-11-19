package study.toy.domain.member.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import study.toy.domain.member.Member;
import study.toy.domain.member.MemberRepositoryTrans;

import java.sql.SQLException;

@Slf4j
public class MemberServiceTransaction {

    private final MemberRepositoryTrans memberRepositoryTrans;

    public MemberServiceTransaction(MemberRepositoryTrans memberRepositoryTrans) {
        this.memberRepositoryTrans = memberRepositoryTrans;
    }

    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        bizLogic(fromId,toId,money);
    }


    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepositoryTrans.findById(fromId);
        Member toMember = memberRepositoryTrans.findById(toId);

        memberRepositoryTrans.update(fromId, fromMember.getMoney()-money);
        validation(toMember);
        memberRepositoryTrans.update(toId, toMember.getMoney() + money);

    }
    private void validation(Member toMember) {
        if (toMember.getLoginId().equals("ex")) {
            log.info("error 이체중 예외 발생= {}",toMember.getLoginId());
            throw new IllegalStateException("이체중 예외 발생");
        }
    }
}
