package shop.mtcoding.blog.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.JoinDTO;

@Controller
public class UserController {

    // 실무
    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {
        // username=ssar&password=1234&email=ssar@nate.com
        System.out.println("username : " + joinDTO.getUsername());
        System.out.println("password : " + joinDTO.getPassword());
        System.out.println("email : " + joinDTO.getEmail());

        return "redirect:/loginForm";
    }

    // // 정상인
    // @PostMapping("/join")
    // public String join(String username, String password, String email) {
    // // username=ssar&password=1234&email=ssar@nate.com
    // System.out.println("username : " + username);
    // System.out.println("password : " + password);
    // System.out.println("email : " + email);
    // return "redirect:/loginForm";
    // }

    // // 비정상
    // @PostMapping("/join")
    // public String join(HttpServletRequest request) throws IOException {
    // // username=ssar&password=1234&email=ssar@nate.com
    // BufferedReader br = request.getReader();

    // // 버퍼에 값이 없어서, 못꺼냄
    // String body = br.readLine();

    // // readLine에서 버퍼가 이미 소모되었음
    // String username = request.getParameter("username");

    // System.out.println("body : " + body);
    // System.out.println("username : " + username);

    // return "redirect:/loginForm";
    // }

    // // 약간 정상
    // // DS(컨트롤러 메서드 찾기, 바디데이터 파싱)
    // // DS가 바디데이터를 파싱안하고 컨트롤러 메서드만 찾은 상황
    // @PostMapping("/join")
    // public String join(HttpServletRequest request) {
    // String username = request.getParameter("username");
    // String password = request.getParameter("password");
    // String email = request.getParameter("email");
    // System.out.println("username : " + username);
    // System.out.println("password : " + password);
    // System.out.println("email : " + email);
    // return "redirect:/loginForm";
    // }

    // ip주소 부여 : 10.5.9.200 -> mtcoding.com:8080 (DNL)
    // 프로세스를 결정하는게 포트번호
    // localhost, 127.0.0.1
    // get요청하는 방법 : a태그-하이퍼링크, form태그-method의 get

    @GetMapping("/joinForm")
    public String joinForm() {
        // templates
        // .mustache
        // templates//user//joinForm.mustache
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
