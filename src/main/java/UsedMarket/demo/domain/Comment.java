package UsedMarket.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)    //파라미터가 없는 기본 생성자를 생성   /빌더쓸때 같이써야함
@Table(name="comments")
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private LocalDateTime createDate;


    @Column(name = "comments")
    private String comment; //댓글내용

    private String writer;

    @Builder
    protected Comment(String comment,String writer,LocalDateTime createDate,Board board){
        this.comment=comment;
        this.writer=writer;
        this.createDate=createDate;
        this.board=board; }
}
