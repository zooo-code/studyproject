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
import study.project.domain.order.service.OrderItemService;
import study.project.page.Pagination;
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

    private final OrderItemService orderItemService;

    @GetMapping
    public String items(@Login Member loginMember, Model model){
        List<Item> allItems = itemService.findAllItems();
        Member member = memberService.findByIdMember(loginMember.getId()).get();
        model.addAttribute("member",member);
        model.addAttribute("items",allItems);
        return "items/allItemList";
    }
    @GetMapping("/myList")
    public String myItemList(@Login Member loginMember,Model model,
                             @RequestParam(defaultValue = "1") int page){
        int allCnt = itemService.myItemList(loginMember.getId()).size();

        //페이지
        Pagination pagination = new Pagination(allCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();
        List<MemberItemDto> memberItems = itemService.myItemListPaging(loginMember.getId(), startIndex, pageSize);
        model.addAttribute("items",memberItems);
        model.addAttribute("page",page);
        model.addAttribute("pagination",pagination);
        model.addAttribute("member",loginMember);
        return "items/MyItemList";
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
                             RedirectAttributes redirectAttributes, Model model){
        if (result.hasErrors()){
            model.addAttribute("member",loginMember);
            return "items/createItemForm";
        }
        Member byIdMember = memberService.findByIdMember(loginMember.getId()).get();

        Item item = new Item(byIdMember, itemForm.getName(), itemForm.getStockQuantity(), itemForm.getPrice());
        Item saveItem = itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId",saveItem.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String item(@Login Member loginMember,
                       @PathVariable Long itemId,
                       Model model,RedirectAttributes redirectAttributes){

        List<Long> Items = itemService.myItem(loginMember.getId());
        if (!Items.contains(itemId)){
            redirectAttributes.addAttribute("status",true);
            return "redirect:/items/myList";
        }
        Optional<Item> findByIdItem = itemService.findByIdItem(itemId);
        model.addAttribute("item",findByIdItem.get());
        model.addAttribute("member",loginMember);
        return "items/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editItem(@Login Member loginMember,
                           @PathVariable("itemId") Long itemId,
                           Model model,RedirectAttributes redirectAttributes){
        List<Long> Items = itemService.myItem(loginMember.getId());
        if (!Items.contains(itemId)){
            redirectAttributes.addAttribute("status",true);
            return "redirect:/items/myList";
        }
        Item item = itemService.findByIdItem(itemId).get();
        model.addAttribute("member",loginMember);
        model.addAttribute("item",item);
        return "items/editItem";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@Login Member loginMember, @PathVariable Long itemId,
                       @ModelAttribute("item")@Validated EditItemForm itemForm,
                       BindingResult result,
                       RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            model.addAttribute("member",loginMember);
            return "items/editItem";
        }
        itemService.edit(itemId,itemForm.getItemName(),itemForm.getPrice(),itemForm.getStockQuantity());
        redirectAttributes.addAttribute("itemId",itemId);
        return "redirect:/items/{itemId}";
    }


    @PostMapping("/{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId, @Login Member loginMember,
                         RedirectAttributes redirectAttributes ){
        List<Long> Items = itemService.myItem(loginMember.getId());
//        다른 회원의 아이템 삭제 하려 할때 금지
        if (!Items.contains(itemId)){
            redirectAttributes.addAttribute("status",true);
            return "redirect:/items/myList";
        }
//        이 아이템이 주문된 아이템인지 확인
        boolean items = orderItemService.findItems(itemId);
        if (items){
            redirectAttributes.addAttribute("noDelete",true);
            redirectAttributes.addAttribute("itemId",itemId);
            return "redirect:/items/{itemId}";
        }

        String possible = itemService.deleteItem(itemId);

        redirectAttributes.addAttribute("possible",possible);
        redirectAttributes.addAttribute("delete", true);
        return "redirect:/items/myList";
    }

}
