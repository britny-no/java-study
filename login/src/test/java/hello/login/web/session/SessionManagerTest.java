package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionManagerTest {
    SessionManager sessionManager = new SessionManager();

    @Test
    void sessionTest(){
        // HttpServletRequest, HttpServletResponse 에는 setCookies, addCookies 없다
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        Member member = new Member();
        sessionManager.createSession(member, response);


        request.setCookies(response.getCookies());


        Object session = sessionManager.getSession(request);
        Assertions.assertThat(session).isEqualTo(member);


        sessionManager.expire(request);
        Object session1 = sessionManager.getSession(request);
        Assertions.assertThat(session1).isEqualTo(null);
    }
}
