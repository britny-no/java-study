package hello.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String fileDir;
    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFilev2(HttpServletRequest request) throws ServletException, IOException {

        Collection<Part> parts = request.getParts();

        for(Part part: parts){
            log.info("=======PART======");
            log.info("name={}", part.getName());

            Collection<String> headerNames = part.getHeaderNames();
            for(String headerName : headerNames){
                log.info("header {} : {}", headerName, part.getHeader(headerName));
            }

            //편의 메서드
            //content-disposition
            log.info("submitFileName={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize());

            InputStream inputStream = part.getInputStream();
            String s = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body={}", s);

            if(StringUtils.hasText(part.getSubmittedFileName())){
                String s1 = fileDir + part.getSubmittedFileName();
                part.write(s1);
            }


        }

        return "upload-form";

    }
}
