package study.toy.web;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import study.toy.domain.item.Item;
import study.toy.domain.item.MemoryItemRepository;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item/items")
@RequiredArgsConstructor
public class ItemController {
    private final MemoryItemRepository memoryItemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = memoryItemRepository.findAll();
        model.addAttribute("items", items);
        return "item/items";
    }

}
