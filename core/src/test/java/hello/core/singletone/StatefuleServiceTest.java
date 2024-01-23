package hello.core.singletone;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefuleServiceTest {
    @Test
    void statefulServiceSingletone(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefuleService s1 = ac.getBean("statefuleService", StatefuleService.class);
        StatefuleService s2 = ac.getBean("statefuleService", StatefuleService.class);

        s1.order("userA", 1000);
        s2.order("userB", 2000);

        int price1 = s1.getPrice();
        System.out.println("price1 = " + price1);
    }

    static class TestConfig{
        @Bean
        public StatefuleService statefuleService(){
            return new StatefuleService();
        }
    }

}