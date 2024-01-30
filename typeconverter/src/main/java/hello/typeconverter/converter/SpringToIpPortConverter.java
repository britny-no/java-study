package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class SpringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("string source {}", source);
        String[] split = source.split(":");
        String Ip = split[0];
        int Port = Integer.parseInt(split[1]);
        return new IpPort(Ip, Port);
    }
}
