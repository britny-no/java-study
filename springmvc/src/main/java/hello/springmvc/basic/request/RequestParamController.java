package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParam1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
//        return "hello-form";
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParam2(
            @RequestParam(value = "username", required = false, defaultValue = "123") String username,
            @RequestParam(value = "age", required = true) int age
    ) throws IOException {
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        return "ok";
//        response.getWriter().write("ok");
//        return "hello-form";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @ModelAttribute HelloData helloData
    ){
//        HelloData helloData = new HelloData();
//        helloData.setAge(age);
//        helloData.setUsername(username);
        log.info("hello = {}", helloData);
//        log.info(helloData)
        return "123";

    }
}
