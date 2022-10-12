package com.sparta.article.domain;

import com.sparta.article.dto.LoginRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private String password;


    public User(LoginRequestDto loginRequestDto) {
        this.username = loginRequestDto.getUsername();
        this.password = loginRequestDto.getPassword();
    }
}
