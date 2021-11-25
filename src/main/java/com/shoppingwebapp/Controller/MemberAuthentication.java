package com.shoppingwebapp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MemberAuthentication {

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "test");
        return "success";
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        return "Session id:" + session.getAttribute("user");
    }
}
