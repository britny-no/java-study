package hello.typeconverter.formatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MyNumberFormatterTest {
    MyNumberFormatter formatter = new MyNumberFormatter();
    @Test
    void parse() {
        try{
            Number parse = formatter.parse("1,000", Locale.KOREA);
            Assertions.assertThat(parse).isEqualTo(1000L);
        }catch (ParseException e){

        }
    }

    @Test
    void print() {
        String print = formatter.print(1000, Locale.KOREA);
        Assertions.assertThat(print).isEqualTo("1,000");
    }
}