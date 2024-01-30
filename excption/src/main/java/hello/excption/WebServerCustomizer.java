package hello.excption;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.management.RuntimeMBeanException;


//@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/400");
        ErrorPage e2= new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
        ErrorPage e3 = new ErrorPage(RuntimeMBeanException.class, "/error-page/500");

        factory.addErrorPages(errorPage, e2, e3);
    }


}
