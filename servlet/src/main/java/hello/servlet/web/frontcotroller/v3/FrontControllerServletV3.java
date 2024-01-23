package hello.servlet.web.frontcotroller.v3;

import hello.servlet.web.frontcotroller.ModelView;
import hello.servlet.web.frontcotroller.MyView;
import hello.servlet.web.frontcotroller.v3.ControllerV3;
import hello.servlet.web.frontcotroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcotroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcotroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    // Mapping 정보
    private Map<String, ControllerV3> controllerV1Map = new HashMap<>();

    public FrontControllerServletV3() {
        this.controllerV1Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        this.controllerV1Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        this.controllerV1Map.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerV1Map.get(requestURI);
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // paramMap
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        MyView myView = viewResolver(mv);

        myView.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(ModelView mv) {
        return new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
    }

    private  Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
