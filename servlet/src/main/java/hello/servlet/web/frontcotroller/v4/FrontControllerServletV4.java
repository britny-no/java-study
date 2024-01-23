package hello.servlet.web.frontcotroller.v4;

import hello.servlet.web.frontcotroller.ModelView;
import hello.servlet.web.frontcotroller.MyView;
import hello.servlet.web.frontcotroller.v3.ControllerV3;
import hello.servlet.web.frontcotroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcotroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcotroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcotroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcotroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcotroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    // Mapping 정보
    private Map<String, ControllerV4> controllerV1Map = new HashMap<>();

    public FrontControllerServletV4() {
        this.controllerV1Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        this.controllerV1Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        this.controllerV1Map.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerV1Map.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);
        MyView myView = viewResolver(viewName);

        myView.render(model, request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }


    private  Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
