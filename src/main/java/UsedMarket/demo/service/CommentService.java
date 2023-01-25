package UsedMarket.demo.service;

import UsedMarket.demo.domain.Board;
import UsedMarket.demo.domain.Comment;
import UsedMarket.demo.dto.CommentDto;
import UsedMarket.demo.repository.BoardRepository;
import UsedMarket.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public List<Comment> findComment(Long id){
        Board board = boardRepository.findById(id).get();
        return commentRepository.findAllByBoard(board); }

    public Long findBoardByComment(Long id){
        Comment comment =commentRepository.findById(id).get();
        return comment.getBoard().getId(); }

    @Transactional
    public void createComment(CommentDto commentDto, String name,Board boardId){
        commentDto.setCreateDate(LocalDateTime.now());

        Comment comment=Comment.builder()
                .comment(commentDto.getComment())
                .createDate(commentDto.getCreateDate())
                .writer(name)
                .board(boardId)
                .build();
        commentRepository.save(comment); }

    @Transactional
    public void deleteComment(Long id){ commentRepository.deleteById(id);}


    @Transactional
    public void update_comment(Long id){
        Comment comment=commentRepository.findById(id).get();
    }
}
