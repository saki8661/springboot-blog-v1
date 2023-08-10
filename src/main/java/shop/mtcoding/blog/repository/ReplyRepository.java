package shop.mtcoding.blog.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blog.dto.ReplyWriteDTO;
import shop.mtcoding.blog.dto.WriteDTO;
import shop.mtcoding.blog.model.Reply;

// IoC컨테이너 컴파운트 스캔 목록
// UserController, BoardController, ReplyController, ErrorController
// UserRepository, BoardRepository, ReplyRepository - 내가 띄움
// EntityManager, HttpSession - Spring이 띄움
@Repository
public class ReplyRepository {

    @Autowired
    private EntityManager em;

    public Reply findById(int id) {
        Query query = em.createNativeQuery("select * from reply_tb where id = :id", Reply.class);
        query.setParameter("id", id);
        return (Reply) query.getSingleResult();
    }

    public List<Reply> findByBoardId(Integer boardId) {
        Query query = em.createNativeQuery("select * from reply_tb where board_id = :boardId", Reply.class);
        query.setParameter("boardId", boardId);
        return query.getResultList();
    }

    @Transactional
    public void save(ReplyWriteDTO replyWriteDTO, Integer userId) {
        Query query = em.createNativeQuery(
                "insert into reply_tb(comment, user_id, board_id) values(:comment, :userId, :boardId)");
        query.setParameter("comment", replyWriteDTO.getComment());
        query.setParameter("userId", userId);
        query.setParameter("boardId", replyWriteDTO.getBoardId());
        query.executeUpdate();

    }

    @Transactional
    public void deleteByReplyId(Integer id) {
        Query query = em.createNativeQuery("delete from reply_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
