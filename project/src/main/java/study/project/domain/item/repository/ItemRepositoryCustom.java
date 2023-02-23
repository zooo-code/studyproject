package study.project.domain.item.repository;

import study.project.domain.item.Item;
import study.project.web.item.dto.MemberItemDto;

import java.util.List;

public interface ItemRepositoryCustom {

    List<MemberItemDto> myItemList(Long memberId);
    List<Item> findItemPaging(int startIndex, int pageSize);
}
