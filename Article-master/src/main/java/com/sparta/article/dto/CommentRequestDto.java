package com.sparta.article.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String nickname;
    private String username;
    private String contents;

}