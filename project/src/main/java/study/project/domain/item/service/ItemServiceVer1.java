package study.project.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.project.domain.item.Item;
import study.project.domain.item.repository.ItemRepository;
import study.project.web.item.dto.MemberItemDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceVer1 implements ItemService{

    private final ItemRepository itemRepository;
    @Override
    @Transactional
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    @Transactional
    @Override
    public String deleteItem(Long itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()){
            itemRepository.delete(item.get());
            return "삭제 완료";
        }
        return "잘못된 명령";

    }

    @Override
    public Optional<Item> findByIdItem(Long itemId) {
        return itemRepository.findById(itemId);
    }



    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<MemberItemDto> myItemList(Long memberId) {
        return itemRepository.myItemList(memberId);
    }

    @Override
    @Transactional
    public void edit(Long id, String name, int price, int stockQuantity) {
        Item item = itemRepository.findById(id).get();
        item.update(name,price,stockQuantity);
    }

    @Override
    public List<Long> myItem(Long memberId) {
        List<Item> byMemberId = itemRepository.findByMemberId(memberId);
        List<Long> itemId = new ArrayList<>();
        for (Item item : byMemberId) {
            itemId.add(item.getId());
        }
        return itemId;
    }

    @Override
    public int findAllCnt() {
        return itemRepository.findAll().size();
    }

    @Override
    public List<Item> findItemPaging(int startIndex, int pageSize) {
        return itemRepository.findItemPaging(startIndex,pageSize);
    }

    @Override
    public List<MemberItemDto> myItemListPaging(Long memberId, int startIndex, int pageSize) {
        return itemRepository.myItemListPaging(memberId,startIndex,pageSize);
    }
}
