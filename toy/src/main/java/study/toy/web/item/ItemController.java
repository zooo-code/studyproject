package study.toy.web.item;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study.toy.domain.item.Item;
import study.toy.domain.item.ItemRepository;
import study.toy.domain.item.MemoryItemRepository;
import study.toy.web.item.form.ItemSaveForm;
import study.toy.web.item.form.ItemUpdateForm;

import javax.validation.constraints.Null;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository ItemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = ItemRepository.findAll();
        model.addAttribute("items", items);
        return "item/items";
    }
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId , Model model){
        Item findItem = ItemRepository.findById(itemId);
        model.addAttribute("item",findItem);
        return "item/item";
    }
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item",new Item());
        return "item/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //특정 필드가 아닌 복합 룰 검증
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
        if (bindingResult.hasErrors()){
            log.info("errors={} ", bindingResult);
            return "/item/addForm";
        }
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = ItemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/item/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = ItemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "item/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        //특정 필드가 아닌 복합 룰 검증
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "item/editForm";
        }
        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());

        itemParam.setQuantity(form.getQuantity());
        ItemRepository.update(itemId, itemParam);
        return "redirect:/item/items/{itemId}";
    }
}
