package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void conversionService(){
        DefaultConversionService defaultConversionService = new DefaultConversionService();
        defaultConversionService.addConverter(new SpringToIpPortConverter());
        defaultConversionService.addConverter(new IntegerToStringConverter());
        defaultConversionService.addConverter(new IpPortToStringConverter());
        defaultConversionService.addConverter(new StringToIntegerConverter());

        Integer convert = defaultConversionService.convert("10", Integer.class);
        Assertions.assertThat(convert).isEqualTo(10);

        String convert2 = defaultConversionService.convert(10, String.class);
        Assertions.assertThat(convert2).isEqualTo("10");

        IpPort convert1 = defaultConversionService.convert("127.0.0.1:8080", IpPort.class);
        Assertions.assertThat(convert1).isEqualTo(new IpPort("127.0.0.1", 8080));

    }
}
