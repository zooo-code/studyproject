package study.project.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.argumentResolver.Login;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ItemService itemService;
    @GetMapping("/")
    public String Home(@Login Member loginMember, Model model){

        if (loginMember == null){
            return "home";
        }
        log.info("home");
        model.addAttribute("member",loginMember);
        List<Item> allItems = itemService.findAllItems();
        model.addAttribute("items",allItems);

        return "loginHome";
    }


}
