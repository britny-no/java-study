package hello.login.web.filter;

import hello.login.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "members/add", "/login", "logout", "/css/*"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        try{
            log.info("인증 체크 필터 시작{}", requestURI);
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 시작{}", requestURI);
                HttpSession session = httpServletRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 로그인 {}", requestURI);
                    httpServletResponse.sendRedirect("/login?redirectURL="+requestURI);
                    return;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }catch(Exception e){
            throw e;// 예외 로깅 가능하지만, 톰캣까지 예외를 보내줘야함
        }finally {
            log.info("인증 체크 필터 종료{}",requestURI);
        }
    }

    /**
     * 화이트 리스트의 경우 인증 체크x
     */

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
