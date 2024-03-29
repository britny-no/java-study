package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//@GetMapping("/")
//public String home(@SessionAttribute(name=SessionConst.LOGIN_MEMBER, required = false) Member member, HttpServletRequest request, Model model) {
//    if(member == null){
//        return "home";
//    }
//
//    model.addAttribute("member", member);
//    return "loginHome";
//
//}

    @GetMapping("/")
    public String home(@Login Member member, Model model) {
        if(member == null){
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";

    }
}