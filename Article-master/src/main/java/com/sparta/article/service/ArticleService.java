package com.sparta.article.service;

import com.sparta.article.domain.Article;
import com.sparta.article.repository.ArticleRepository;
import com.sparta.article.dto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor    // final을 못받을 수도 있을 거 같으니까 장치를 해줘야함. : final로 선언된 녀석이 있으면 그거 생성할 때 무조건 같이 넣어줘
@Service // spring에게 이게 service라는 것을 알려줘야함.
public class ArticleService {

    // memoRepository가 꼭 필요한 거다!를 알려줘야 한다 => final
    private final ArticleRepository articleRepository;

    @Transactional  // 이게 진짜 DB에 반영돼야한다고 알려줘야 함.
    public Long update(Long id, ArticleRequestDto requestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        article.update(requestDto);
        return article.getId();
    }
}
// 생성자와 서비스를 알려주는 거 하나랑 transactional이라는 어노테이션을 메소드 위에 꼭 넣어줘야함.

