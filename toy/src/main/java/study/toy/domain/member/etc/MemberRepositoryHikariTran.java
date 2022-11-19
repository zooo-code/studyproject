package study.toy.domain.member.etc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import study.toy.domain.member.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;


@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryHikariTran {
    private final DataSource dataSource;

    private void close(Connection con, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeConnection(con);
    }
    private Connection getConnection() throws SQLException {
        Connection con = dataSource.getConnection();
        log.info("get connection={}, class={}", con, con.getClass());
        return con;
    }
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, login_id, name, password) values(?,?,?,?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
//            데이터베이스에 전달할 SQL과 파라미터로 전달할 데이터들을 준비한다.
            pstmt = con.prepareStatement(sql);
//            SQL의 첫,두번째 ? 에 값을 지정한다. 문자이므로 setString 을 사용한다.
            pstmt.setLong(1, member.getId());
            pstmt.setString(2, member.getLoginId());
//            SQL의 세번째 ? 에 값을 지정한다
            pstmt.setString(3, member.getName());
            pstmt.setString(4,member.getPassword());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }


    public Member findById(String login_id) throws SQLException {
        String sql = "select * from member where login_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, login_id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setLoginId(rs.getString("login_id"));
                member.setPassword(rs.getString("password"));
                System.out.println("member.getPassword() = " + member.getPassword());
                member.setName(rs.getString("name"));
                System.out.println("member.getName() = " + member.getName());
                member.setId(rs.getLong("member_id"));
                System.out.println("member.getId() = " + member.getId());
                member.setMoney(rs.getInt("money"));
                System.out.println("member.getMoney() = " + member.getMoney());

                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + login_id);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, rs);
        }

    }
    public Member findById(Connection con, String login_id) throws SQLException {
        String sql = "select * from member where login_id = ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, login_id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setLoginId(rs.getString("login_id"));
                member.setPassword(rs.getString("password"));
                System.out.println("member.getPassword() = " + member.getPassword());
                member.setName(rs.getString("name"));
                System.out.println("member.getName() = " + member.getName());
                member.setId(rs.getLong("member_id"));
                System.out.println("member.getId() = " + member.getId());
                member.setMoney(rs.getInt("money"));
                System.out.println("member.getMoney() = " + member.getMoney());

                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + login_id);
            }
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(pstmt);

        }

    }
    public void update(Connection con ,String login_id, int money) throws SQLException {
        String sql = "update member set money=? where login_id=?";

        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, login_id);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {

            JdbcUtils.closeStatement(pstmt);
        }
    }
    public void update(String login_id, int money) throws SQLException {
        String sql = "update member set money=? where login_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, login_id);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }
    public void delete(String login_id) throws SQLException {
        String sql = "delete from member where login_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, login_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }

    }

}
