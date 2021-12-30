package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Dao.ProductRepository;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/productManagement")
public class ProductManageController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MemberRepository memberRepository;

    @PostMapping(path = "/delete") //delete
    public String delete(@RequestParam int itemID,HttpSession session) {
        Optional<Product> Optional = productRepository.findById(itemID);
        Object memberID = session.getAttribute("userId");
        if(memberID != null){
            Member member = memberRepository.findById(Integer.parseInt(memberID.toString())).get();
            if(Optional.isPresent() && member.getUsername().equals("admin")) {
                Product p = Optional.get();
                productRepository.delete(p);
                return "Success!";
            }else{
                return "Fail!";
            }
        }else{
            return "Fail!";
        }
    }
    @PostMapping(path = "/update") //update
    public String update(@RequestParam int itemID, @RequestParam String name, @RequestParam String description, @RequestParam String quantity, @RequestParam String price, @RequestParam("file") MultipartFile image, HttpSession session) {
        Optional<Product> Optional = productRepository.findById(itemID);
        Object memberID = session.getAttribute("userId");
        if(Integer.parseInt(quantity) < 0 || Integer.parseInt(price) < 0){
            return "Fail!";
        }
        if(memberID != null){
            Member member = memberRepository.findById(Integer.parseInt(memberID.toString())).get();
            if(Optional.isPresent() && member.getUsername().equals("admin")) {
                Product p = Optional.get();
                p.setName(name);
                p.setDescription(description);
                p.setQuantity(quantity);
                p.setPrice(price);
                try {
                    p.setImage(image.getBytes());
                } catch (IOException e) {
                    return "Image_Fail!";
                }
                productRepository.save(p);
                return "Success!";
            }else{
                return "Fail!";
            }
        }else{
            return "Fail!";
        }
    }
    @PostMapping(path = "/add") //add
    public String add(@RequestParam String name, @RequestParam String description, @RequestParam String quantity, @RequestParam String price, @RequestParam("file") MultipartFile image, HttpSession session) {
        Object memberID = session.getAttribute("userId");
        if(Integer.parseInt(quantity) < 0 || Integer.parseInt(price) < 0 || name.equals("") || description.equals("")){
            return "Fail!";
        }
        if(memberID != null){
            Member member = memberRepository.findById(Integer.parseInt(memberID.toString())).get();
            if(member.getUsername().equals("admin")) {
                Product p = new Product();
                p.setName(name);
                p.setDescription(description);
                p.setQuantity(quantity);
                p.setPrice(price);
                try {
                    p.setImage(image.getBytes());
                } catch (IOException e) {
                    return "Image_Fail!";
                }
                productRepository.save(p);
                return "Success!";
            }else{
                return "Fail!";
            }
        }else{
            return "Fail!";
        }
    }
}
