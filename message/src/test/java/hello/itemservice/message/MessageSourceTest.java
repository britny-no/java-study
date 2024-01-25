package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage(){
        String hello = messageSource.getMessage("hello", null, null);
        Assertions.assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){
        Assertions.assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefault(){
        String noCode = messageSource.getMessage("no_code", null, "!23", null);
        Assertions.assertThat(noCode).isEqualTo("!23");

//        Assertions.assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void arguments(){
        String zzzzz = messageSource.getMessage("hello.name", new Object[]{"ㅋㅋㅋㅋ"}, null);
        Assertions.assertThat(zzzzz).isEqualTo("안녕 ㅋㅋㅋㅋ");
    }

    @Test
    void locale(){
        String zzzzz = messageSource.getMessage("hello", null, Locale.KOREA);
        Assertions.assertThat(zzzzz).isEqualTo("안녕");
    }
}
