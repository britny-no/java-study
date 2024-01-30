package hello.typeconverter.formatter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.StringToIntegerConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

public class FormattinConversionServiceTest {
    @Test
    public void formattingConversionService(){
        DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
//        defaultFormattingConversionService.addConverter(new StringToIntegerConverter());
//        defaultFormattingConversionService.addConverter(new IntegerToStringConverter());

        defaultFormattingConversionService.addFormatter(new MyNumberFormatter());

        Integer convert = defaultFormattingConversionService.convert("127", Integer.class);
        Assertions.assertThat(convert).isEqualTo(127);

        String convert1 = defaultFormattingConversionService.convert(1000, String.class);
        Assertions.assertThat(convert1).isEqualTo("1,000");

        Integer convert2 = defaultFormattingConversionService.convert("1,000", Integer.class);
        Assertions.assertThat(convert2).isEqualTo(1000);
    }
}
