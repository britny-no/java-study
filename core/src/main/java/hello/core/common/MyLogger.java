package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private  String uuid;
    private String requestUrl;

    public void setRequestURL(String requestUrl){
        this.requestUrl = requestUrl;
    }
    public void log(String messgae){
        System.out.println("["+uuid+"]  " + messgae);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"]  request scope" + this );
    }

    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"]  request scope" + this );
    }

}
