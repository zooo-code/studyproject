package study.project.domain.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.project.domain.item.Item;
import study.project.domain.item.search.ItemSearch;
import study.project.web.item.dto.MemberItemDto;

import java.util.List;

public interface ItemRepositoryCustom {

    List<MemberItemDto> myItemList(Long memberId);
    List<Item> findItemPaging(int startIndex, int pageSize);
    List<MemberItemDto> myItemListPaging(Long memberId, int startIndex, int pageSize);
    List<Item> itemSearchPaging(ItemSearch itemSearch , int startIndex, int pageSize);
}
