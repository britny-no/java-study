package hello.excption.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ErrorPAgeController {
    public static final String ERROR_EXCEPTION =
            "jakarta.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE =
            "jakarta.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI =
            "jakarta.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME =
            "jakarta.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE =
            "jakarta.servlet.error.status_code";


    @RequestMapping("/error-page/404")
    public String error404(HttpServletRequest request, HttpServletResponse response){
        log.info("error 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String error500(HttpServletRequest request, HttpServletResponse response){
        log.info("error 500");
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> error500Json(HttpServletRequest request, HttpServletResponse response){
        log.info("apijson 500");

        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("status", request.getAttribute(ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());

        Integer errorCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        ㄱ
        return new ResponseEntity<>(result, HttpStatus.valueOf(errorCode));
    }
}
