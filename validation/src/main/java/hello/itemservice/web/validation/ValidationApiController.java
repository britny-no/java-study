package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("  ")
public class ValidationApiController {
    @PostMapping("/add")
    public Object addItem(
            @Validated @RequestBody ItemSaveForm form,
            BindingResult bindingResult
            ){
        log.info("call");

        if(bindingResult.hasErrors()){
            log.info("오류 발생 error={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("성공 ");
        return form;
    }
}
