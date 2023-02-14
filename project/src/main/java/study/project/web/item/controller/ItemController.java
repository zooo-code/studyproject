package study.project.web.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.item.dto.ItemForm;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final MemberService memberService;
    @GetMapping("/create")
    public String createItem(Model model){
        model.addAttribute("itemForm",new ItemForm());
        return "items/createItemForm";
    }

//    @PostMapping("/create")
//    public String create(@Validated ItemForm form, BindingResult result){
//        if (result.hasErrors()){
//            return "items/createItemForm";
//        }
//        Optional<Member> findMember = memberService.findByIdMember();
//        Item item = new Item(findMember.get(), form.getName(), form.getStockQuantity(), form.getPrice());
//        itemService.saveItem(item);
//        return ;
//    }
}
