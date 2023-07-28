package shop.mtcoding.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "user_tb")
@Entity // ddl-auto가 create
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 번호증가 전략이 DB마다 다름
    // identity전략 설정된 DB전략을 따라감
    // Auto_Increment전략
    // 시퀀스전략
    // 테이블전략
    private Integer id;

    @Column(nullable = false, length = 20, unique = true)
    private String username;
    @Column(nullable = false, length = 20, unique = true)
    private String password;
    @Column(nullable = false, length = 20, unique = true)
    private String email;

}
