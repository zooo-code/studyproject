package study.project.web.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.project.domain.item.Item;
import study.project.domain.item.service.ItemService;
import study.project.domain.member.Member;
import study.project.domain.member.service.MemberService;
import study.project.web.argumentResolver.Login;
import study.project.web.item.dto.ItemForm;
import study.project.web.item.dto.MemberItemDto;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping
    public String items(Model model){
        List<Item> allItems = itemService.findAllItems();
        model.addAttribute("items",allItems);
        return "items/allItemList";
    }
    @GetMapping("/create")
    public String createItem(@Login Member loginMember , Model model){
        ItemForm itemForm = new ItemForm();

        itemForm.setUserId(loginMember.getId());
        model.addAttribute("member",loginMember);
        model.addAttribute("itemForm",itemForm);
        return "items/createItemForm";
    }
    @PostMapping("/create")
    public String createItem(@ModelAttribute Member loginMember, @Validated ItemForm itemForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "items/createItemForm";
        }
        System.out.println("loginMember.getId() = " + loginMember.getId());
        Item item = new Item(loginMember, itemForm.getName(), itemForm.getStockQuantity(), itemForm.getPrice());
        Item saveItem = itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId",saveItem.getId());
        redirectAttributes.addAttribute("memberId",loginMember.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/items/{memberId}/{itemId}";
    }
    @GetMapping("/{memberId}/{itemId}")
    public String item(@Login Member loginMember,
                       @PathVariable Long memberId,
                       @PathVariable Long itemId,
                       Model model){
        Optional<Item> findByIdItem = itemService.findByIdItem(itemId);
        model.addAttribute("item",findByIdItem.get());
        model.addAttribute("member",loginMember);
        return "items/item";
    }

    @GetMapping("/{memberId}/{itemId}/Item")
    public String editItem(@PathVariable("itemId") Long itemId, @PathVariable("memberId") Long memberId,
                       Model model){
        Member member = memberService.findByIdMember(memberId).get();
        Optional<Item> byIdItem = itemService.findByIdItem(itemId);
        model.addAttribute("member",member);
        model.addAttribute("myItems",byIdItem.get());
        return "items/editItem";
    }
//    @GetMapping("/{memberId}/itemList")
//    public String MyItemList(@PathVariable("memberId") Long memberId){
//
//    }

    @GetMapping("/{memberId}")
    public String myItemList(@Login Member loginMember,
                             @PathVariable Long memberId, Model model){
        List<MemberItemDto> memberItems = itemService.myItemList(loginMember.getId());
        model.addAttribute("items",memberItems);
        return "items/MyItemList";
    }
}
