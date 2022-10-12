package com.sparta.article.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter // private 변수는 Getter을 통해 가지고 와야 한다!
@MappedSuperclass // Entity가 생성시간과 수정시간을 자동으로 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다.
public abstract class Timestamped {

    // 생성시간
    @CreatedDate
    private LocalDateTime createdAt;

    //수정시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
