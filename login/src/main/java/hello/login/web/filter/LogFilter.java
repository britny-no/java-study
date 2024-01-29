package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        log.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filter do");

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        String string = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}][{}] ", string, requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]", string, requestURI);
        }
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        log.info("filter end");
    }
}
