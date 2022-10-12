package com.sparta.article.domain;

import com.sparta.article.dto.ArticleRequestDto;
import com.sparta.article.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Article extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // id가 자동으로 하나씩 늘도록
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String comment;

//    @Column(nullable = false)
//    private String memoId;

    public Article(String username, String title, String contents, String password) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public Article(ArticleRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(ArticleRequestDto requestDto) { // ArticleService에서 update에 전달받기로 한게 MemoRequestDto이다.
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

//    public void comment(CommentRequestDto commentRequestDto) {
//        this.memoId = commentRequestDto.getMemoId();
//        this.username = commentRequestDto.getUsername();
//        this.contents = commentRequestDto.getContents();
//    }
}
