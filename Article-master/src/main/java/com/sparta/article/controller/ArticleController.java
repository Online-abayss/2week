package com.sparta.article.controller;

import com.sparta.article.domain.Article;
import com.sparta.article.repository.ArticleRepository;
import com.sparta.article.dto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controller는 Method(GET, POST, PUT, DELETE) 를 실행하는 것.
@RequiredArgsConstructor // final
@RestController // new MemoController를 스프링이 알아서 다해주게 하는 것.
public class ArticleController {

    private final ArticleRepository articleRepository;    // 나머지를 위해서는 Repository가 필요함. - 필수적
    private final com.sparta.article.service.ArticleService articleService;  // 업데이트를 위해서는 service가 필요함. - 필수적

    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody ArticleRequestDto requestDto) {    // 생성할 메모의 데이터를 몰고다니느 RequestDto를 받아옴
        // => 요청이 날아올 때  body라는 녀석에 들어있는 거를 오른쪽에 넣어줘!!@RequestBody!!
        Article article = new Article(requestDto);   //requestDto에 있는 데이터가 알아서 memo에 들어가서 채워넣을 것
        return articleRepository.save(article);   //위의 article를 저장하기 -> 저장은 Repository.save()
    }

    @GetMapping("/api/articles")
    public List<Article> readAllArticles() {
        return articleRepository.findAllByOrderByModifiedAtDesc(); //다찾아서 ModifiedAt기준으로 내림차순으로 가져와라
    }

    @GetMapping("/api/articles/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleRepository.findById(id);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deletePost(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto){
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            System.out.println("article.get().getPassword() : " + article.get().getPassword() + " requestDto.getPassword() : " + requestDto.getPassword());
            if (article.get().getPassword().equals(requestDto.getPassword())) {
                articleRepository.deleteById(id);
            } else {
                System.out.println("비밀번호 오류");
                return 0L;
            }
        }
        return id;
    }

    @PutMapping("/api/articles/{id}")
    public  Long updateArticle(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto){
        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            System.out.println("article.get().getPassword() : " + article.get().getPassword() + " requestDto.getPassword() : " + requestDto.getPassword());
            if (article.get().getPassword().equals(requestDto.getPassword())) {
                articleService.update(id, requestDto);
            } else {
                System.out.println("비밀번호 오류");
                return 0L;
            }
        }
        return id;
    }


//    @PutMapping("/api/articles/{id}")
//    public Long updateMemo(@PathVariable Long id, @RequestBody ArticleRequestDto requestDto) {
//        articleService.update(id, requestDto);   // 업데이트는 service에서 함.
//        return id;
//    }
//
//    @DeleteMapping("/api/articles/{id}")
//    public Long deleteMemo(@PathVariable Long id) { //@PathVariable : 주소에 있는 변수이다~, 경로에 이름이 똑같은 변수에 넣어줄게
//        articleRepository.deleteById(id);
//        return id;
//    }
}
