package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.ReplyWriteDTO;
import shop.mtcoding.blog.model.Reply;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.repository.ReplyRepository;

@Controller
public class ReplyController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ReplyRepository replyRepository;

    @PostMapping("/reply/save")
    public String save(ReplyWriteDTO replyWriteDTO) {
        // comment, boardId 유효성 검사
        if (replyWriteDTO.getComment() == null || replyWriteDTO.getComment().isEmpty()) {
            return "redirect:/40x";
        }
        if (replyWriteDTO.getBoardId() == null) {
            return "redirect:/40x";
        }

        // 인증 검사
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm"; // 401 인증
        }

        // 댓글 쓰기
        replyRepository.save(replyWriteDTO, sessionUser.getId());

        return "redirect:/board/" + replyWriteDTO.getBoardId();
    }

    @PostMapping("/reply/{id}/delete")
    public String deleteReply(@PathVariable Integer id, Integer boardId) { // 1. PathVariable 값 받기
        System.out.println("테스트id : " + id);
        System.out.println("테스트boardId : " + boardId);
        // 유효성 검사
        if (boardId == null) {
            return "redirect:/40x";
        }
        System.out.println("테스트:1");

        // 인증체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        System.out.println("테스트:2");

        // 권한체크
        Reply reply = replyRepository.findById(id);
        if (reply.getUser().getId() != sessionUser.getId()) {
            return "redirect:/40x"; // 403
        }
        System.out.println("테스트:3");

        // 핵심로직
        replyRepository.deleteByReplyId(id);
        System.out.println("테스트:4");

        return "redirect:/board/" + boardId;

    }
}
