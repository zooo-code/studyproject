package study.project.web.item.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.page.Pagination;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/items")
public class AdminItemController {

    private final ItemService itemService;

    @GetMapping
    public String allItems(@RequestParam(defaultValue = "1") int page){

        int allCnt = itemService.findAllItems().size();
        Pagination pagination = new Pagination(allCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
//        이름으로 검색 가능하게 구현 해야함
        List<Item> itemPaging = itemService.findItemPaging(startIndex, pageSize);

        return "items/allItemList";
    }

    @GetMapping("item/{itemId}")
    public String item(@PathVariable Long itemId){
        return "items/item";
    }
}
