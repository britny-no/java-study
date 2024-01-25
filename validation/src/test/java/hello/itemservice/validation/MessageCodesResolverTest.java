package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void setMessageCodesResolver(){
        String[] message = codesResolver.resolveMessageCodes("required", "items");
        for(String a: message){
//            System.out.println("message = " + a);
        }
//        System.out.println();
        Assertions.assertThat(message).contains("required.items", "required");
    }

    @Test
    void setMessageCodesResolver2(){
        String[] message = codesResolver.resolveMessageCodes("required", "items", "itemName", String.class);
        for(String a: message){
            System.out.println("message = " + a);
        }
//        System.out.println();
        Assertions.assertThat(message).contains("required.items.itemName");
    }
}
