package com.sparta.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {
    private String username;
    private String contents;
    private String title;
    private String password;
}
