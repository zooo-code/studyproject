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
        itemForm.setUserLoginId(loginMember.getLoginId());
        itemForm.setUserId(loginMember.getId());
        model.addAttribute("member",loginMember);
        model.addAttribute("itemForm",itemForm);
        return "items/createItemForm";
    }
    @PostMapping("/create")
    public String createItem(@Validated ItemForm form, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            return "items/createItemForm";
        }
      
        Member member = memberService.findByIdMember(form.getUserId()).get();

        Item item = new Item(member, form.getName(), form.getStockQuantity(), form.getPrice());
        Item saveItem = itemService.saveItem(item);
        redirectAttributes.addFlashAttribute("itemId",saveItem.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/items/{memberId}/{itemId}";
    }
    @GetMapping("/{memberId}/{itemId}")
    public String item(@PathVariable Long memberId,
                       @PathVariable Long itemId,
                       Model model){

        Optional<Item> findByIdItem = itemService.findByIdItem(itemId);
        model.addAttribute("item",findByIdItem.get());
        return "items/item";
    }

//    @GetMapping("/{memberId}/{itemId}/Item")
//    public String Item(@PathVariable("itemId") Long itemId, @PathVariable("memberId") Long memberId,
//                       Model model){
//        itemService
//        model.addAttribute("myItems");
//        return ;
//    }
//    @GetMapping("/{memberId}/itemList")
//    public String MyItemList(@PathVariable("memberId") Long memberId){
//
//    }

}
