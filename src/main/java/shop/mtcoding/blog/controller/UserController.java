package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.dto.UserUpdateDTO;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpSession session; // request는 가방, session은 서랍

    // localhost:8080//check?username=ssar
    @GetMapping("/check")
    public ResponseEntity<String> check(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<String>("유저네임이 중복 되었습니다", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("유저네임을 사용할 수 있습니다", HttpStatus.OK);
    }

    // @ResponseBody
    // @GetMapping("/test/login")
    // public String testLogin() {
    // User sessionUser = (User) session.getAttribute("sessionUser");
    // if (sessionUser == null) {
    // return "로그인이 되지 않았습니다";
    // } else {
    // return "로그인 됨 : " + sessionUser.getUsername();
    // }
    // }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO) {
        // validation check (유효성 검사)
        if (loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()) {
            return "redirect:/40x";
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {
            return "redirect:/40x";
        }

        // 핵심 기능

        try {
            User user = userRepository.findByUsername(loginDTO.getUsername());
            if (BCrypt.checkpw(loginDTO.getPassword(), user.getPassword())) {
                // loginDTO.getPassword() = 클라이언트 입력값
                // user.getPassword() = DB의 user_tb에 저장된 password 데이터
                session.setAttribute("sessionUser", user);
                return "redirect:/";
            } else {
                return "redirect:/loginForm";
            }
        } catch (Exception e) {
            return "redirect:/exLoginErr";
        }
    }

    // 실무업글
    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {
        // validation check (유효성 검사)
        if (joinDTO.getUsername() == null || joinDTO.getUsername().isEmpty()) {
            return "redirect:/40x";
        }
        if (joinDTO.getPassword() == null || joinDTO.getPassword().isEmpty()) {
            return "redirect:/40x";
        }
        if (joinDTO.getEmail() == null || joinDTO.getEmail().isEmpty()) {
            return "redirect:/40x";
        }
        // DB에 해당 username이 있는지 체크해보기
        User user = userRepository.findByUsername(joinDTO.getUsername());
        if (user != null) {
            return "redirect:/50x";
        }
        // 비밀번호를 BCrypt로 암호화하여 저장
        String encryptedPassword = BCrypt.hashpw(joinDTO.getPassword(), BCrypt.gensalt());
        joinDTO.setPassword(encryptedPassword);

        userRepository.save(joinDTO); // 핵심 기능
        return "redirect:/loginForm";
    }

    // // 실무
    // @PostMapping("/join")
    // public String join(JoinDTO joinDTO) {
    // // username=ssar&password=1234&email=ssar@nate.com
    // System.out.println("username : " + joinDTO.getUsername());
    // System.out.println("password : " + joinDTO.getPassword());
    // System.out.println("email : " + joinDTO.getEmail());

    // return "redirect:/loginForm";
    // }

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
        session.invalidate(); // 세션 무효화 (내 서랍을 비우는 것)
        return "redirect:/";
    }

    @PostMapping("/user/{id}/update")
    public String update(@PathVariable Integer id, UserUpdateDTO userUpdateDTO) {
        String encryptedPassword = BCrypt.hashpw(userUpdateDTO.getPassword(), BCrypt.gensalt());
        userUpdateDTO.setPassword(encryptedPassword);
        userRepository.update(userUpdateDTO, id);
        return "redirect:/loginForm";

    }
}
