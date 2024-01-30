package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @GetMapping("/api")
    public String api(
            @RequestParam("data") Integer data
    ){
        log.info("data {}", data);
        return "ok";

    }

    @GetMapping("/ip-port")
    public String ipPort(
            @RequestParam("ipPort") IpPort ipPort
            ){
        log.info("ipport, {}", ipPort);
        return "ok";
    }
}
