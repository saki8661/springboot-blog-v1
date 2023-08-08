package shop.mtcoding.blog.repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.ReplyWriteDTO;
import shop.mtcoding.blog.dto.WriteDTO;

@Repository
public class ReplyRepository {

    @Autowired
    private EntityManager em;

    @Transactional
    public void save(ReplyWriteDTO replyWriteDTO, Integer userId) {
        Query query = em.createNativeQuery(
                "insert into reply_tb(comment, user_id, board_id) values(:comment, :userId, :boardId)");
        query.setParameter("comment", replyWriteDTO.getComment());
        query.setParameter("userId", userId);
        query.setParameter("boardId", replyWriteDTO.getBoardId());

        System.out.println("테스트 유저 아이디 : " + userId);
        System.out.println("테스트 보드 : " + replyWriteDTO.getBoardId());
        System.out.println("테스트 내용 : " + replyWriteDTO.getComment());
        query.executeUpdate();

    }

}