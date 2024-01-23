package hello.core.scan.filter;

import java.lang.annotation.*;


// 해당 애노테이션이 붙으면 컴포넌트 스캔에 포함시킨다 의미
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {


}
