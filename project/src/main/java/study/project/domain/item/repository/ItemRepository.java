package study.project.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.project.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
