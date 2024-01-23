package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {
    @Test
    void prototypeBeanFine(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find p1");
        PrototypeBean p1 = ac.getBean(PrototypeBean.class);
        System.out.println("find p2");
        PrototypeBean p2 = ac.getBean(PrototypeBean.class);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);

        Assertions.assertThat(p1).isNotSameAs(p2);
        p1.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("start");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("closed");
        }

    }
}
