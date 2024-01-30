package hello.typeconverter.controller;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
@RestController
@Slf4j
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterEdit(Model model){
        Form form = new Form();
        form.setNumber(10000);
        form.setLocaleDateTime(LocalDateTime.now());
        log.info("form, {}", form);

        return "123";
    }

    @GetMapping("/formatter/test")
    public String formatterTest(@ModelAttribute Form form){
        log.info("{}", LocalDateTime.now());
        log.info("form, {}", form.getLocaleDateTime());

        return "123";
    }

    @Data
    public static class Form{
        @NumberFormat(pattern = "##,###")
        private Integer number;

        @DateTimeFormat(pattern = "YYYY-MM-DD hh:mm:SS")
        private LocalDateTime localeDateTime;
    }
}
