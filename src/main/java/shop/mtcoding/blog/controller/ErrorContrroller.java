package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorContrroller {

    @GetMapping("/exLoginErr")
    public String exLogin() {
        return "error/exLoginErr";
    }

    @GetMapping("/40x")
    public String ex40x() {
        return "error/ex40x";
    }

    @GetMapping("/50x")
    public String ex50x() {
        return "error/ex50x";
    }

}
