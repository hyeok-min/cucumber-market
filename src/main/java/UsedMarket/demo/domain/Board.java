package UsedMarket.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)    //파라미터가 없는 기본 생성자를 생성   /빌더쓸때 같이써야함
@Getter
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR")
public class Board {

    @Id @GeneratedValue(generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "board",cascade = CascadeType.REMOVE) //게시글삭제시 댓글도 삭제
    @OrderBy("id asc")  //댓글정렬
    private List<Comment> comment=new ArrayList<>();

    private String title;   //제목
    private String writer;  //작성자
    private String content; //내용
    private LocalDateTime createDate;   //게시판,댓글 생성시간   //나중에 extends로 하게 함
    private Long count; //조회수
    private Long edit_count;    //수정count
    private String filename;
    private String filepath;

    @Builder
    protected Board(String title,String writer,String content,LocalDateTime createDate,Long count,Category category,String filepath,String filename, Long edit_count){
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.createDate=createDate;
        this.count=count;
        this.category=category;
        this.filename=filename;
        this.filepath=filepath;
        this.edit_count=edit_count;
    }
    public void setCount(Long count) {
        this.count = count;
    }


//    =================수정=====================
    public void setEdit_count(Long edit_count){this.edit_count=edit_count;}
    public void updateTitle(String title){this.title=title;}
    public void updateContent(String content){this.content=content;}
    public void updateFilename(String filename){this.filename=filename;}
    public void updateFilepath(String filepath){this.filepath=filepath;}
    public void updateCreateDate(LocalDateTime createDate){this.createDate=createDate;}

}
