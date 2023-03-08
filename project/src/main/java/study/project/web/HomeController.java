package study.project.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import study.project.domain.item.Item;
import study.project.domain.item.search.ItemSearch;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.page.Pagination;
import study.project.web.argumentResolver.Login;

import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ItemService itemService;
//    @GetMapping("/")
    public String Home(@Login Member loginMember, Model model,
                       @RequestParam(defaultValue = "1") int page){

        if (loginMember == null){
            return "home";
        }
        log.info("home");
        model.addAttribute("member",loginMember);
//        List<Item> allItems = itemService.findAllItems();

        //페이지
        int allCnt = itemService.findAllCnt();
        Pagination pagination = new Pagination(allCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<Item> itemPaging = itemService.findItemPaging(startIndex, pageSize);

        model.addAttribute("items",itemPaging);
        model.addAttribute("pagination",pagination);
        model.addAttribute("page",page);

        //끝
        return "loginHome";
    }
    @GetMapping("/")
    public String Home(@Login Member loginMember,
                       @RequestParam(defaultValue = "1") int page,
                       @ModelAttribute("itemSearch") ItemSearch itemSearch , Model model){

        if (loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);

        //페이지
        int allCnt = itemService.itemSearchPageable(itemSearch).size();
        Pagination pagination = new Pagination(allCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<Item> items = itemService.itemSearchPageable(itemSearch, startIndex, pageSize);

        model.addAttribute("items",items);
        model.addAttribute("pagination",pagination);
        model.addAttribute("page",page);

        //끝
        return "loginHome";
    }

}
