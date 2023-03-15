package study.project.web.item.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import study.project.domain.item.Item;
import study.project.domain.item.search.ItemSearch;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Grade;
import study.project.domain.member.Member;
import study.project.page.Pagination;
import study.project.web.argumentResolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/items")
public class AdminItemController {

    private final ItemService itemService;

    @GetMapping
    public String allItems(@Login Member loginMember,
                           @ModelAttribute("itemSearch") ItemSearch itemSearch,
                           @RequestParam(defaultValue = "1") int page,
                           Model model){

        if (loginMember == null || loginMember.getGrade() == Grade.CUSTOMER){
            return "home";
        }
        model.addAttribute("member",loginMember);

        int allCnt = itemService.itemSearchPageable(itemSearch).size();
        Pagination pagination = new Pagination(allCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
//        이름으로 검색 가능하게 구현 해야함
        List<Item> items = itemService.itemSearchPageable(itemSearch,startIndex, pageSize);
        model.addAttribute("items",items);
        model.addAttribute("pagination",pagination);
        model.addAttribute("page",page);

        return "items/admin/allItemList";
    }

    @GetMapping("item/{itemId}")
    public String item(@PathVariable Long itemId){



        return "items/item";
    }
}
