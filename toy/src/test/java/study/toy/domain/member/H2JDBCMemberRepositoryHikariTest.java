package study.toy.domain.member;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.toy.domain.member.etc.H2JDBCMemberRepositoryHikari;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static study.toy.web.connection.ConnectionConst.*;

@Slf4j
class H2JDBCMemberRepositoryHikariTest {
        H2JDBCMemberRepositoryHikari repository;

    @BeforeEach
    void beforeEach() throws Exception {
        //기본 DriverManager - 항상 새로운 커넥션 획득
        //DriverManagerDataSource dataSource =
        // new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        //커넥션 풀링: HikariProxyConnection -> JdbcConnection
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new H2JDBCMemberRepositoryHikari(dataSource);
    }

    @Test
    void crud() throws SQLException, InterruptedException {
        log.info("start");
        //save
        Member member = new Member(3L,"member", "test","test1");
        repository.save(member);
        //findById
        Member memberById = repository.findById(member.getLoginId());
        assertThat(memberById).isNotNull();
        //update: money: 10000 -> 20000
        repository.update(member.getLoginId(), 20000);
        Member updatedMember = repository.findById(member.getLoginId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);
        //delete
        repository.delete(member.getLoginId());
        assertThatThrownBy(() -> repository.findById(member.getLoginId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}