package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@CrossOrigin(allowCredentials = "true", origins = "http://localhost:3000/")//set CORS
@RestController // This means that this class is a Controller
public class AuthorizationController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping(path = "authorize")
    public String authorize(HttpSession httpSession) {
        Object memberId =  httpSession.getAttribute("userId");
        if(memberId == null) {//The session is not binded from a user.
            return "Fail!";
        }
        else {
            String identity;
            Member member;
            member = memberRepository.findById(Integer.parseInt(memberId.toString())).get();
            identity = member.getAdmin() ? "admin" : "member";
            return identity;
        }
    }
}
