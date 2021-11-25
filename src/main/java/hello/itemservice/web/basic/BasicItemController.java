package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    //    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item savedItem = itemRepository.save(new Item(itemName, price, quantity));
        model.addAttribute("item", savedItem);
        return "basic/item";
    }


    //    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        Item savedItem = itemRepository.save(item);
//        model.addAttribute("item", savedItem);
        return "basic/item";
    }


    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item, Model model) {
        itemRepository.save(item);
        return "basic/item";
    }


    // 테스트용
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("달고나", 1000, 33));
        itemRepository.save(new Item("사과", 2000, 7));
    }

}
