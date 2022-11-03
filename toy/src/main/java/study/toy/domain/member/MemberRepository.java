package study.toy.domain.member;

public interface MemberRepository {
    public void save(Member member);
    public Member findByUserId(Long id);
}
