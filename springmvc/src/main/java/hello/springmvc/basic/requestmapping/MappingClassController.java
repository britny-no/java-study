package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String user(){
        return "get users";
    }

    @PostMapping
    public String addUser(){
        return "post users";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        log.info("use id ={}", userId);
        return "getuserId =" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUSer(@PathVariable String userId){
        log.info("update id ={}", userId);
        return "update id =" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        log.info("delete id ={}", userId);
        return "delete id =" + userId;
    }


}
