package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Dao.ProductRepository;
import com.shoppingwebapp.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping(path = "/product")
public class ProductController {
    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /*@PostMapping(path = "/create")
    public String createProduct(@RequestParam String name, @RequestParam String description, @RequestParam String quantity, @RequestParam String price, @RequestParam("file") MultipartFile image) {
        Product product;
        try {
            product = new Product(name, description, quantity, price, image.getBytes());
        }
        catch (Exception e) {
            return "Constructing Fail!";
        }
        productRepository.save(product);
        return "Success!";
    }*/

    @GetMapping(path = "/list")
    public Iterable<Product> listProduct() {
        return productRepository.findAll();
    }


}
