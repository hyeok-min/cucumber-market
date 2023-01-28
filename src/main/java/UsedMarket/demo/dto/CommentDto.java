package UsedMarket.demo.dto;

import UsedMarket.demo.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
//@NoArgsConstructor
public class CommentDto {
    private String comment;
    private String writer;
    private Board board;
    private LocalDateTime createDate;
}
