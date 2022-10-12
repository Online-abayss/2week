package com.sparta.article.domain;

import com.sparta.article.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    public Comment(CommentRequestDto commentRequestDto){
        this.nickname = commentRequestDto.getNickname();
        this.username = commentRequestDto.getUsername();
        this.contents = commentRequestDto.getContents();
    }

    public void update(CommentRequestDto commentRequestDto){
        this.nickname = commentRequestDto.getNickname();
        this.username = commentRequestDto.getUsername();
        this.contents = commentRequestDto.getContents();
    }
}