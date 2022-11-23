package study.toy.domain.member.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import study.toy.domain.member.Member;
import study.toy.domain.member.MemberRepository;
import study.toy.domain.member.MemberRepositoryTrans;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * 트랜잭션 - DataSource, transactionManager 자동 등록
 */
@Slf4j
@SpringBootTest
class MemberServiceTransactionTest {


    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberServiceTransaction memberServiceTransaction;


    @TestConfiguration
    static class TestConfig{


        private final DataSource dataSource;

        public TestConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Bean
        MemberRepository memberRepository() {
            return new MemberRepositoryTrans(dataSource);
        }

        @Bean
        MemberServiceTransaction memberServiceTransaction(){
            return new MemberServiceTransaction(memberRepository());
        }
    }

    @AfterEach
    void after() {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    void AopCheck() {
        log.info("memberService class={}", memberServiceTransaction.getClass());
        log.info("memberRepository class={}", memberRepository.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(memberServiceTransaction)).isTrue();
//        지금은 memberRepository가 bean 에 등록되어 있는 상태라 안댐
        Assertions.assertThat(AopUtils.isAopProxy(memberRepository)).isFalse();
    }
    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        //given
        Member memberA = new Member(1L,MEMBER_A,MEMBER_A,"test!");
        Member memberB = new Member(2L, MEMBER_B, MEMBER_B,"test!");

        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.update(memberA.getLoginId(),10000);
        memberRepository.update(memberB.getLoginId(),10000);
        //when
        memberServiceTransaction.accountTransfer(memberA.getLoginId(), memberB.getLoginId(), 2000);

        //then
        Optional<Member> findMemberA = memberRepository.findByLoginId(memberA.getLoginId());
        Optional<Member> findMemberB = memberRepository.findByLoginId(memberB.getLoginId());

        assertThat(findMemberA.get().getMoney()).isEqualTo(8000);
        assertThat(findMemberB.get().getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx() throws SQLException {
        //given
        Member memberA = new Member(1L,MEMBER_A,MEMBER_A,"test!");
        Member memberEx = new Member(2L,MEMBER_EX, MEMBER_EX,"test!");

        memberRepository.save(memberA);
        memberRepository.save(memberEx);
        memberRepository.update(memberA.getLoginId(),10000);
        memberRepository.update(memberEx.getLoginId(),10000);
        //when
        assertThatThrownBy(() ->
                memberServiceTransaction.accountTransfer(memberA.getLoginId(), memberEx.getLoginId(), 2000))
                .isInstanceOf(IllegalStateException.class);
        //then
        Optional<Member> findMemberA = memberRepository.findByLoginId((memberA.getLoginId()));
        Optional<Member> findMemberB = memberRepository.findByLoginId((memberEx.getLoginId()));
        assertThat(findMemberA.get().getMoney()).isEqualTo(10000);
        assertThat(findMemberB.get().getMoney()).isEqualTo(10000);
    }

}