package hello.core.lifecycle;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient   {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출" + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connected");
    }
    
    public void call(String msg){
        System.out.println("msg = " + msg);
    }

    public void disconnect(){
        System.out.println("disconnected");
    }


    @PostConstruct
    public void init() throws Exception {
        connect();
        call("초기화 메세지 연결");
    }


    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
