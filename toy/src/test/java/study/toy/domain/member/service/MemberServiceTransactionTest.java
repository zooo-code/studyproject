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
import study.toy.domain.member.MemberRepositoryTrans;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
    private MemberRepositoryTrans memberRepositoryTrans;

    @Autowired
    private MemberServiceTransaction memberServiceTransaction;


    @TestConfiguration
    static class TestConfig{


        private final DataSource dataSource;

        public TestConfig(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Bean
        MemberRepositoryTrans memberRepositoryTrans() {
            return new MemberRepositoryTrans(dataSource);
        }

        @Bean
        MemberServiceTransaction memberServiceTransaction(){
            return new MemberServiceTransaction(memberRepositoryTrans());
        }
    }

    @AfterEach
    void after() throws SQLException {
        memberRepositoryTrans.delete(MEMBER_A);
        memberRepositoryTrans.delete(MEMBER_B);
        memberRepositoryTrans.delete(MEMBER_EX);
    }

    @Test
    void AopCheck() {
        log.info("memberService class={}", memberServiceTransaction.getClass());
        log.info("memberRepository class={}", memberRepositoryTrans.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(memberServiceTransaction)).isTrue();
        Assertions.assertThat(AopUtils.isAopProxy(memberRepositoryTrans)).isFalse();
    }
    @Test
    @DisplayName("정상 이체")
    void accountTransfer() throws SQLException {
        //given
        Member memberA = new Member(1L,MEMBER_A,MEMBER_A,"test!");

        System.out.println("memberA.getMoney() = " + memberA.getMoney());
        Member memberB = new Member(2L, MEMBER_B, MEMBER_B,"test!");

        memberRepositoryTrans.save(memberA);
        memberRepositoryTrans.save(memberB);
        memberRepositoryTrans.update(memberA.getLoginId(),10000);
        memberRepositoryTrans.update(memberB.getLoginId(),10000);
        //when
        memberServiceTransaction.accountTransfer(memberA.getLoginId(), memberB.getLoginId(), 2000);

        //then
        Member findMemberA = memberRepositoryTrans.findByLoginId(memberA.getLoginId());
        Member findMemberB = memberRepositoryTrans.findByLoginId(memberB.getLoginId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체중 예외 발생")
    void accountTransferEx() throws SQLException {
        //given
        Member memberA = new Member(1L,MEMBER_A,MEMBER_A,"test!");
        Member memberEx = new Member(2L,MEMBER_EX, MEMBER_EX,"test!");

        memberRepositoryTrans.save(memberA);
        memberRepositoryTrans.save(memberEx);
        memberRepositoryTrans.update(memberA.getLoginId(),10000);
        memberRepositoryTrans.update(memberEx.getLoginId(),10000);
        System.out.println("findMemberA = " + memberA);
        System.out.println("memberEx = " + memberEx);
        //when
        assertThatThrownBy(() ->
                memberServiceTransaction.accountTransfer(memberA.getLoginId(), memberEx.getLoginId(), 2000))
                .isInstanceOf(IllegalStateException.class);

        //then
        Member findMemberA = memberRepositoryTrans.findByLoginId((memberA.getLoginId()));
        Member findMemberB = memberRepositoryTrans.findByLoginId((memberEx.getLoginId()));
        System.out.println("findMemberA = " + findMemberA);
        System.out.println("findMemberB = " + findMemberB);
        log.info("error ={} {}",findMemberA.getMoney(),findMemberB.getMoney());
        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }

}