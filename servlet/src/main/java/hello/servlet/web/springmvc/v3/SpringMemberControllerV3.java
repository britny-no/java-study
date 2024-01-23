package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value="/new-form", method= RequestMethod.GET)
    @GetMapping("/new-form")
    public String process(){
        return  "new-form";
    }
//    @RequestMapping(value="/save", method= RequestMethod.POST)
    @PostMapping("/save")
    public String process2(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);

        memberRepository.save(member);

//        ModelAndView mv = new ModelAndView("save-result");
//        mv.addObject("member", member);
        model.addAttribute("member", member);
        return "save-result";
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String process3(Model model) {
        List<Member> members = memberRepository.findAll();
//        ModelAndView mv = new ModelAndView("members");
        model.addAttribute("members", members);
        return "members";
    }
}
