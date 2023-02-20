package study.project.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.project.domain.item.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long>, ItemRepositoryCustom {

    List<Item> findByMemberId(Long memberId);
}
