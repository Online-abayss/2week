package com.sparta.article.service;

import com.sparta.article.domain.Comment;
import com.sparta.article.dto.CommentRequestDto;
import com.sparta.article.dto.CommentResponseDto;
import com.sparta.article.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    public CommentResponseDto write(CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto edit(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = findId(id);
        comment.update(commentRequestDto);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public Boolean delete(Long id){
        commentRepository.deleteById(id);

        return true;
    }

    @Transactional
    public Comment findId(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return comment;
    }


}
