package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    public static final String SESSION_COOKIE_NAME = "mySessionID";
    private  static  Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */

    public void createSession(Object value, HttpServletResponse response){
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
    }

    /**
     * 세션 조회
     */

    public Object getSession(HttpServletRequest request) {
        Cookie cooke = findCooke(request, SESSION_COOKIE_NAME);
        if(cooke == null){
            return null;
        }else{
            return sessionStore.get(cooke.getValue());
        }
    }

    public Cookie findCooke(HttpServletRequest request, String cookiName){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(cookiName)).findFirst().orElse(null);
    }

    /**
     * 세션 만료
     */

    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCooke(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }

    }



}