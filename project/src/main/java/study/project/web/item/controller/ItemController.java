package study.project.web.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import study.project.web.item.dto.EditItemForm;
import study.project.web.item.dto.ItemForm;
import study.project.web.item.dto.MemberItemDto;

import java.util.List;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping
    public String items(@Login Member loginMember, Model model){
        List<Item> allItems = itemService.findAllItems();
        Member member = memberService.findByIdMember(loginMember.getId()).get();
        model.addAttribute("member",member);
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
    public String createItem(@Login Member loginMember, @Validated ItemForm itemForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "items/createItemForm";
        }
        Optional<Member> byIdMember = memberService.findByIdMember(loginMember.getId());

        Item item = new Item(byIdMember.get(), itemForm.getName(), itemForm.getStockQuantity(), itemForm.getPrice());
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

    @GetMapping("/{memberId}")
    public String myItemList(@Login Member loginMember,
                             @PathVariable Long memberId, Model model){
        List<MemberItemDto> memberItems = itemService.myItemList(loginMember.getId());
        Member member = memberService.findByIdMember(memberId).get();
        model.addAttribute("items",memberItems);
        model.addAttribute("member",member);
        return "items/MyItemList";
    }

    @GetMapping("/{memberId}/{itemId}/edit")
    public String editItem(@Login Member loginMember,
                           @PathVariable("itemId") Long itemId, @PathVariable("memberId") Long memberId,
                           Model model){
        log.info("editForm");
        Member member = memberService.findByIdMember(memberId).get();
        Item item = itemService.findByIdItem(itemId).get();
        model.addAttribute("member",member);
        model.addAttribute("item",item);
        return "items/editItem";
    }

    @PostMapping("/{memberId}/{itemId}/edit")
    public String edit(@PathVariable Long memberId, @PathVariable Long itemId,
                       @ModelAttribute("item")@Validated EditItemForm itemForm,
                       BindingResult result,
                       RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "items/editItem";
        }
        itemService.edit(itemId,itemForm.getItemName(),itemForm.getPrice(),itemForm.getStockQuantity());
        redirectAttributes.addAttribute("memberId",memberId);
        redirectAttributes.addAttribute("itemId",itemId);

        return "redirect:/items/{memberId}/{itemId}";
    }

    @GetMapping("/order/{itemId}")
    public String orderItem(@PathVariable Long itemId){
        return "xx";
    }
}
