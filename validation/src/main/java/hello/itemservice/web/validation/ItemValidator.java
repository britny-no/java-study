package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> Class) {
        return Item.class.isAssignableFrom(Class);
    }

    @Override
    public void validate(Object target, Errors bindingResult) {
        Item item = (Item) target;


        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName", "required");
        }

        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.rejectValue("price", "range", new Object[]{100, 10000}, null);
        }

        if(item.getQuantity() == null || item.getQuantity() > 9999){
            bindingResult.rejectValue("quantity", "max", new Object[]{100}, null);
        }



    }
}
