package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    public void StringtoInteger(){
        StringToIntegerConverter stringToIntegerConverter = new StringToIntegerConverter();
        Integer convert = stringToIntegerConverter.convert("10");
        Assertions.assertThat(convert).isEqualTo(10);
    }

    @Test
    public void IntegerToString(){
        IntegerToStringConverter integerToStringConverter = new IntegerToStringConverter();
        String convert = integerToStringConverter.convert(10);
        Assertions.assertThat(convert).isEqualTo("10");
    }

    @Test
    public void StringToIpPort(){
        SpringToIpPortConverter springToIpPortConverter = new SpringToIpPortConverter();

        IpPort convert = springToIpPortConverter.convert("127.0.0.1:8080");
        Assertions.assertThat(convert).isEqualTo(new IpPort("127.0.0.1", 8080));
//        Assertions.assertThat(convert.getPort()).isEqualTo(0101);
    }
}
