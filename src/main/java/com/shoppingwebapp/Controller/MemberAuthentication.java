package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/auth") // This means URL's start with /demo (after Application path)
public class MemberAuthentication {
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping(path = "/login") //Create member
    public String login(@RequestParam String username, @RequestParam String password,HttpSession session) {
        Iterable<Member> iterable = memberRepository.findByUsername(username);

        for (Member member : iterable) {
            if(member.getPassword().equals(password)){
                if(session.getAttribute(username) == null){
                    session.setAttribute(username, member.getId());
                }
                return "Success";
            }
        }
        return "Fail";
    }


    @GetMapping("/set")//set a session id for "test" then return to client
    public String set(HttpSession session) {
        session.setAttribute("user", "test");
        return "success";
    }

    @GetMapping("/get")//use the session id on the client to get the username
    public String get(HttpSession session) {
        return "Session id:" + session.getAttribute("user");
    }
}
