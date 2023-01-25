package UsedMarket.demo.repository;

import UsedMarket.demo.domain.Board;
import UsedMarket.demo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByBoard(Board board);
}
