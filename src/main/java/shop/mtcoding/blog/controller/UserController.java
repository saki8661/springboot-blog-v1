package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    // ip주소 부여 : 10.5.9.200 -> mtcoding.com:8080 (DNL)
    // 프로세스를 결정하는게 포트번호
    // localhost, 127.0.0.1
    // get요청하는 방법 : a태그-하이퍼링크, form태그-method의 get
    @GetMapping("/joinForm")
    public String joinForm() {
        // templates
        // .mustache
        //
        return "user/joinForm"; // viewResolver
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "/user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
