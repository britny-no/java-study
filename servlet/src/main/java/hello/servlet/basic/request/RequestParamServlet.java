package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Enumeration<String>
        System.out.println("전체 파람 조회");
        request.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName+" = " + request.getParameter(paramName)));
        
        String username = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("username = " + username);
        System.out.println("age = " + age);

        String[] usernames = request.getParameterValues("username");
        for(String name: usernames){
            System.out.println("name = " + name);
        }

    }
}
