package study.project.domain.item.service;

import study.project.domain.item.Item;
import study.project.web.item.dto.MemberItemDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item saveItem(Item item);
    String deleteItem(Long itemId);
    Optional<Item> findByIdItem(Long itemId);

    List<Item> findAllItems();

    List<MemberItemDto> myItemList(Long memberId);

    void edit(Long id, String name, int price, int stockQuantity);

    List<Long> myItem(Long memberId);
    int findAllCnt();
    List<Item> findItemPaging(int startIndex, int pageSize);
    List<MemberItemDto> myItemListPaging(Long memberId, int startIndex, int pageSize);
}
