package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {
    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
    public String helloBasic(){
        log.info("hi ={}", "123");
        return "!23";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String id){
        log.info("hi ={}", id);
        return id;
    }

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }
}
