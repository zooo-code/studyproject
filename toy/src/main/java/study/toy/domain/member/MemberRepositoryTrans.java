package study.toy.domain.member;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepositoryTrans implements MemberRepository{


    private final JdbcTemplate template;
    public MemberRepositoryTrans(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member)  {
        String sql = "insert into member(member_id, login_id, name, password) values(?,?,?,?)";
        template.update(sql,member.getId(),member.getLoginId(),member.getName(),member.getPassword());
        return member;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Optional<Member> findByLoginId(String login_id)  {
        String sql = "select * from member where login_id = ?";
        return Optional.ofNullable(template.queryForObject(sql, memberRowMapper(), login_id));

    }
    @Override
    public void update(String login_id, int money)  {
        String sql = "update member set money=? where login_id=?";
        template.update(sql, money, login_id);
    }
    @Override
    public void delete(String login_id) {
        String sql = "delete from member where login_id=?";

        template.update(sql, login_id);

    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("member_id"));
            member.setLoginId(rs.getString("login_id"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }
}
