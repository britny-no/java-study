package hello.excption.api;

import hello.excption.exception.BadRequestException;
import hello.excption.filter.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(
            @PathVariable("id") String id
    ){
        if(id.equals("ex")){
            throw new RuntimeException("error!!");
        }

        if(id.equals("bad")){
            throw new IllegalArgumentException("error!!");
        }

        if(id.equals("user-ex")){
            throw new UserException("error!!");
        }


        log.info("123");
        return new MemberDto(id, "member"+id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String MemberId;
        private String name;
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatutsEx1(){
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatutsEx2(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "zzz", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex3")
    public String defaultException(
            @RequestParam("data") Integer data
    ){
        return "ok";
    }


}
