package com.sparta.article.dto;

import com.sparta.article.domain.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private String nickname;
    private String username;
    private String contents;

    public CommentResponseDto(Comment comment) {
        this.nickname = comment.getNickname();
        this.username = comment.getUsername();
        this.contents = comment.getContents();
    }
}