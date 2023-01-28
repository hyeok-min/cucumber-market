package UsedMarket.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
//@NoArgsConstructor
public class CommunityDto {
    private String content;
    private String writer;
    private LocalDateTime createTime;
}
