package com.sparta.article.controller;

import com.sparta.article.domain.Article;
import com.sparta.article.domain.Comment;
import com.sparta.article.dto.ArticleRequestDto;
import com.sparta.article.repository.CommentRepository;
import com.sparta.article.dto.CommentRequestDto;
import com.sparta.article.dto.CommentResponseDto;
import com.sparta.article.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @GetMapping("/api/comments")
    public List<Comment> readAllArticles() {
        return commentRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/comments/{id}")
    public Optional<Comment> getComment(@PathVariable Long id) {
        return commentRepository.findById(id);
    }

    @PostMapping("/api/comments")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto) {    // 생성할 메모의 데이터를 몰고다니느 RequestDto를 받아옴
        // => 요청이 날아올 때  body라는 녀석에 들어있는 거를 오른쪽에 넣어줘!!@RequestBody!!
        Comment comment = new Comment(commentRequestDto);   //requestDto에 있는 데이터가 알아서 memo에 들어가서 채워넣을 것
        return commentRepository.save(comment);   //위의 article를 저장하기 -> 저장은 Repository.save()
    }

//
//    @PostMapping("/api/comments")
//    public ResponseEntity<CommentResponseDto> getComment(@RequestBody CommentRequestDto commentRequestDto) {
//        CommentResponseDto commentResponseDto = commentService.write(commentRequestDto);
//        return ResponseEntity.ok()
//                .body(commentResponseDto);
//    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.edit(id, commentRequestDto);
        return ResponseEntity.ok()
                .body(commentResponseDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        Boolean isDeleteComment = commentService.delete(id);
        return ResponseEntity.ok()
                .body(isDeleteComment);
    }
}