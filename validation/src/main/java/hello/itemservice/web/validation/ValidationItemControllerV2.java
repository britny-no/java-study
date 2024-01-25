package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @InitBinder
    public  void init(WebDataBinder dataBinder){
        dataBinder.addValidators(itemValidator);
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }
//@PostMapping("/add")
//public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,  RedirectAttributes redirectAttributes, Model model) {
//        // 검증 오류 결과를 보관
////    Map<String, String> errors = new HashMap<>();
//
//    // 검증 로직
//    if(!StringUtils.hasText(item.getItemName())){
//        bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수 입니다"));
//    }
//
//    if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
//        bindingResult.addError(new FieldError("item", "price", "가격 문제"));
////        errors.put("price", "가격 문제");
//    }
//
//    if(item.getQuantity() == null || item.getQuantity() > 9999){
//        bindingResult.addError(new FieldError("item", "quantity", "수량 문제"));
////        errors.put("quantity", "수량 문제");
//    }
//
//    // 특정 필드가 아닌 복합 룰 검증
//
//    if(item.getPrice() != null && item.getQuantity() != null){
//        int i = item.getPrice() * item.getQuantity();
//        if (i<10000){
//            bindingResult.addError(new ObjectError("item", "가격*수량 에러"));
////            errors.put("globalError", "가격*수량 문제");
//        }
//    }
//
//    if(bindingResult.hasErrors()){
//        log.info("errors={}", bindingResult);
//        return "validation/v2/addForm";
//    }
//
//
//    Item savedItem = itemRepository.save(item);
//    redirectAttributes.addAttribute("itemId", savedItem.getId());
//    redirectAttributes.addAttribute("status", true);
//    return "redirect:/validation/v2/items/{itemId}";
//}

//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult,  RedirectAttributes redirectAttributes, Model model) {
//        // 검증 오류 결과를 보관
////    Map<String, String> errors = new HashMap<>();
//        log.info("123123123");
//        // 검증 로직
//        if(!StringUtils.hasText(item.getItemName())){
//            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수 입니다"));
//        }
//
////        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
////            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격 문제"));
//////        errors.put("price", "가격 문제");
////        }
//
//        if(item.getQuantity() == null || item.getQuantity() > 9999){
//            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량 문제"));
////        errors.put("quantity", "수량 문제");
//        }
//
//        // 특정 필드가 아닌 복합 룰 검증
//
//        if(item.getPrice() != null && item.getQuantity() != null){
//            int i = item.getPrice() * item.getQuantity();
//            if (i<10000){
//                bindingResult.addError(new ObjectError("item", "가격*수량 에러"));
////            errors.put("globalError", "가격*수량 문제");
//            }
//        }
//
//        if(bindingResult.hasErrors()){
//            log.info("errors={}", bindingResult);
//            return "validation/v2/addForm";
//        }
//
//
//        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/v2/items/{itemId}";
//    }

//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult,  RedirectAttributes redirectAttributes, Model model) {
//        // 검증 오류 결과를 보관
////    Map<String, String> errors = new HashMap<>();
//        log.info("123123123");
//        // 검증 로직
//        if(!StringUtils.hasText(item.getItemName())){
//            bindingResult.addError(new FieldError("item", "itemName",
//                    item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
////            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
//        }
//
//        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
//            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 100000}, "가격 문제"));
////        errors.put("price", "가격 문제");
//        }
//
//        if(item.getQuantity() == null || item.getQuantity() > 9999){
//            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{10}, "수량 문제"));
////        errors.put("quantity", "수량 문제");
//        }
//
//        // 특정 필드가 아닌 복합 룰 검증
//
//        if(item.getPrice() != null && item.getQuantity() != null){
//            int i = item.getPrice() * item.getQuantity();
//            if (i<10000){
//                bindingResult.addError(new ObjectError("item", "가격*수량 에러"));
////            errors.put("globalError", "가격*수량 문제");
//            }
//        }
//
//        if(bindingResult.hasErrors()){
//            log.info("errors={}", bindingResult);
//            return "validation/v2/addForm";
//        }
//
//
//        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        redirectAttributes.addAttribute("status", true);
//        return "redirect:/validation/v2/items/{itemId}";
//    }
@PostMapping("/add")
public String addItemV5(@Validated  @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
    // 검증 오류 결과를 보관
    // 검증 로직
//    itemValidator.validate(item, bindingResult);
    if (bindingResult.hasErrors()) {
        log.info("errors={}", bindingResult);
        return "validation/v2/addForm";
    }

    Item savedItem = itemRepository.save(item);
    redirectAttributes.addAttribute("itemId", savedItem.getId());
    redirectAttributes.addAttribute("status", true);
    return "redirect:/validation/v2/items/{itemId}";
}
    private static boolean hasError(Map<String, String> errors) {
        return !errors.isEmpty();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

