package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;


    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 100));
        itemRepository.save(new Item("itemB", 20000, 200));
    }
    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

//    @GetMapping("/{itemId}")
//    public String item(@PathVariable("itemId") long itemId, Model model){
//        Item item = itemRepository.findById(itemId);
//        model.addAttribute("item", item);
//        return "basic/item";
//    }

    @GetMapping("/{id}")
    public String item(@ModelAttribute Item item, Model model){
        Item findItem = itemRepository.findById(item.getId());
        model.addAttribute("item", findItem);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addform";
    }

//    @PostMapping("/add")
    public String save(
            @RequestParam String itemName,
            @RequestParam("price") int price,
            @RequestParam("quantity") Integer quantity,
            Model model
                       ){
//        itemRepository.s
        Item item = new Item();
        item.setQuantity(quantity);
        item.setItemName(itemName);
        item.setPrice(price);

        itemRepository.save(item);
        List<Item> all = itemRepository.findAll();

        model.addAttribute("items", all);
        return "basic/items";
    }

    @PostMapping("/add")
    public String addItemV2(
            @ModelAttribute Item item,
            RedirectAttributes redirectAttributes
    ){
        itemRepository.save(item);
        log.info("id={}", item.getId());
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id,@ModelAttribute Item item, Model model){
//        item.setId(id);
        itemRepository.update(id, item);
//        model.addAttribute(item);
        return "redirect:/basic/items/{id}";
    }

}
