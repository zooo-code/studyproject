package jpa.jpashop.web.item.controller;
import jpa.jpashop.domain.item.Item;
import jpa.jpashop.domain.item.itemcategory.Book;
import jpa.jpashop.domain.item.itemcategory.Category;
import jpa.jpashop.domain.item.itemcategory.SelectItem;
import jpa.jpashop.domain.item.service.ItemService;

import jpa.jpashop.web.item.form.ItemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("item",new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String create(@Validated @ModelAttribute("item") ItemForm form,
                         BindingResult result){
        if (result.hasErrors()){
            log.info("errors={} ", result);
            return "items/createItemForm";
        }
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
        itemService.saveItem(item);
        return "redirect:/items";
    }
    /**
     * 상품 목록
     */
    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping(value = "/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        ItemForm form = new ItemForm();

        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    /**
     * 상품 수정
     */
    @PostMapping(value = "/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") ItemForm form) {
        itemService.updateItem(itemId, form);
        return "redirect:/items";
    }

//
//    @GetMapping(value = "/{itemId}/delete")
//    public String deleteItem(@PathVariable Long itemId, Model model){
//        Item item = itemService.deleteItem(itemId);
//        model.addAttribute("deleteItem", item);
//        return "items/itemDeleteForm";
//
//    }



//    @GetMapping(value = "/category")
//    public String selectItem(Model model){
//        model.addAttribute("selectItem",new SelectItem());
//        return "items/category";
//    }

//    @PostMapping("/category")
//    public String selectItemCategory(@ModelAttribute("selectItem") SelectItem selectItem ,Model model){
//        Category category = selectItem.getCategory();
//
//
//        for (Category cate : Category.values()){
//            if (cate == category){
//                Class<Category> declaringClass = category.getDeclaringClass();
//                model.addAttribute("form", declaringClass);
//                System.out.println(category);
//                return "items/Book";
//            }
//        }
//
//        return "redirect:/items/category";
//    }
}
