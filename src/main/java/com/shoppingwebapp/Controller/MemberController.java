package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/member") // This means URL's start with /demo (after Application path)
public class MemberController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private MemberRepository memberRepository;

    @PostMapping(path = "/create") //Create member
    @ResponseBody
    public String createNewUser(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        member.setPassword(password);
        memberRepository.save(member);
        return "Saved";
    }

    @PostMapping(path = "/readByUsername") //Read specific members
    @ResponseBody
    public Iterable<Member> readMember(@RequestParam String username) {
        return memberRepository.findByUsername(username);
    }

    @PostMapping(path = "/updateByUsername") //update specific members
    @ResponseBody
    public String searchMember(@RequestParam String username, @RequestParam String password) {
        Iterable<Member> iterable = memberRepository.findByUsername(username);
        for (Member member : iterable) {
            member.setPassword(password);
            memberRepository.save(member);
        }
        return "Updated";
    }

    @PostMapping(path = "/deleteByUsername") // Delete member
    @ResponseBody
    public String deleteUser(@RequestParam String username) {
        Iterable<Member> iterable = memberRepository.findByUsername(username);
        for (Member member : iterable) {
            memberRepository.delete(member);
        }
        return "Deleted";
    }

    @GetMapping(path = "/listAll")
    @ResponseBody
    public Iterable<Member> getAllUsers() {
        return memberRepository.findAll();
    }
}