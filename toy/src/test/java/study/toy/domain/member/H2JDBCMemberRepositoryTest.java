package study.toy.domain.member;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static study.toy.web.connection.ConnectionConst.*;


@Slf4j
class H2JDBCMemberRepositoryTest {

    H2JDBCMemberRepository h2JDBCMemberRepository = new H2JDBCMemberRepository();
    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member(3L,"member", "test","test1");
        h2JDBCMemberRepository.save(member);

        //findById
        Member findMember = h2JDBCMemberRepository.findById(member.getLoginId());

        //        isEqualTo() : findMember.equals(member) 를 비교한다. 결과가 참인 이유는 롬복의 @Data 는 해당
        //객체의 모든 필드를 사용하도록 equals() 를 오버라이딩 하기 때문이다.
        log.info("findMember= {}", findMember);
        log.info("member = {}", member);
        Assertions.assertThat(member).isEqualTo(findMember);


        //update: money: 10000 -> 20000
        h2JDBCMemberRepository.update(member.getLoginId(),20000);
        Member updateMember = h2JDBCMemberRepository.findById(member.getLoginId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        //delete
        h2JDBCMemberRepository.delete(member.getLoginId());
        assertThatThrownBy(() -> h2JDBCMemberRepository.findById(member.getLoginId()))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }


    @Test
    void dataSourceDriverManager() throws SQLException {
    //DriverManagerDataSource - 항상 새로운 커넥션 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }
    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링: HikariProxyConnection(Proxy) -> JdbcConnection(Target)
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");
        useDataSource(dataSource);
        Thread.sleep(1000); //커넥션 풀에서 커넥션 생성 시간 대기
    }
}