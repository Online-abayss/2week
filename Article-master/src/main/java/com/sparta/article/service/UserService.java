package com.sparta.article.service;

import com.sparta.article.domain.RefreshToken;
import com.sparta.article.dto.LoginRequestDto;
import com.sparta.article.dto.SignupRequestDto;
import com.sparta.article.dto.TokenDto;
import com.sparta.article.domain.User;
import com.sparta.article.repository.RefreshTokenRepository;
import com.sparta.article.repository.UserRepository;
import com.sparta.article.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepositroy;
    private final JwtProvider jwtProvider;

    // 회원가입
    public String registerUser(SignupRequestDto signupRequestDto) throws IllegalAccessException {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String samepassword = signupRequestDto.getSamepassword();

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()) {
            throw new IllegalAccessException("중복 닉네임 확인!");
        }
        if(!password.equals(samepassword)) {
            throw new IllegalAccessException("비밀번호가 서로 다릅니다!");
        }
        password = passwordEncoder.encode(signupRequestDto.getPassword());
        LoginRequestDto dto = LoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();
        User user = new User(dto);
        userRepository.save(user);
        return user.getUsername()+" 가입완료!";
    }

    //로그인
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepositroy.save(refreshToken);

        response.addHeader("Access-Token", tokenDto.getGrantType()+" "+tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());

        return authentication.getName()+" 로그인 완료!";
    }
}
