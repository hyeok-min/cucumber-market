package UsedMarket.demo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)    //파라미터가 없는 기본 생성자를 생성   /빌더쓸때 같이써야함
@Getter
@SequenceGenerator(name = "COMMUNITY_SEQ_GENERATOR")
public class Community {
    @Id
    @GeneratedValue(generator = "COMMUNITY_SEQ_GENERATOR")
    @Column(name = "community_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    private String writer;  //작성자
    private String content; //내용
    private LocalDateTime createDate;

    @Builder
    protected Community(String writer,String content,LocalDateTime createDate){
        this.writer=writer;
        this.content=content;
        this.createDate=createDate;
    }
}
