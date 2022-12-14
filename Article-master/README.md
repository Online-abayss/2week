# Article
## Spring 입문주차 과제
### : 회원가입, 로그인, 댓글 작성/수정/삭제 기능이 추가된 나만의 블로그 백엔드 서버 만들기


## __<API 명세서>__
![image](https://user-images.githubusercontent.com/108795153/186638567-b317b62f-6e30-4c51-a38d-ae5bd46a5e95.png)

### __<유스케이스 다이어그램>__
![유스케이스 다이어그램](https://user-images.githubusercontent.com/108795153/186639042-6daf4911-3653-4b86-b671-d35bdfebf838.jpg)



___
___

### __<요구사항>__

1. 전체 게시글 목록 조회 API
+ 제목, 작성자명, 작성날짜를 조회하기
+ 작성 날짜 기준으로 내림차순 정렬하기
+ AccessToken이 없어도 조회가능하게 하기

2. 게시글 작성 API
+ AccessToken이 있고, 유효한 Token일 때(==로그인 상태일 때)만 작성 가능하게 하기
+ 제목 작성 내용을 입력하기

3. 게시글 조회 API
+ 제목, 작성자명, 작성날짜, 작성내용을 조회하기
+ AccessToken이 없어도 조회가능하게 하기

4. 게시글 수정 API
+ AccessToken이 있고, 유효한 Token이면서 해당 게시글 작성자만 수정가능학 하기
+ 제목, 작성자명, 작성내용을 수정되게 하기

5. 게시글 삭제 API
+ AccessToken이 있고, 유효한 Token이면서 해당 게시글 작성자만 삭제 가능하게 하기
+ 글과 댓글이 함게 삭제되게 하기

### __<유의사항>__

1. 회원가입 API 
+ 닉네임, 비밀번호, 비밀번호 확인을 request에서 전달받기
+ 닉네임은 최소 4자이상, 12이하 알파벳 대소문자, 숫자로 구성
+ 비밀번호는 최소 4자이상, 32자이하 알파벳 소문자, 숫자로 구성
+ 비밀번호 확인은 비밀번호와 정확하게 일치하기

2. 로그인 API
+ 닉네임, 비밀번호를 request에서 전달받기
+ 로그인 버튼을 누른 경우 닉네임과 비밀번호가 데이터베이스에 등록됐는지 확인하기
+ 로그인 성공 시, JWT를 활용하여 AccessToken을 발급하고, 발급한 AccessToken은 Header의 Access-Token에 담아서 반환하기
+ 로그인 성공 시, JWT를 활용하여 RefreshToken을 발급하고, 발급한 RefreshToken은 Header의 Refresh-Token에 담아서 반환하기

3. 로그인 검사 
+ (회원가입, 로그인, 게시글목록 조회, 게시글 조회, 댓글 목록 조회)API를 제외하고 모두 AccessToken, RefreshToken을 전달한 경우만 정상 response를 전달받을 수 있도록 하기
+ cf. Authorization에 담긴 AccessToken으로 사용자 판단

4. 댓글 목록 조회 API
+ AccessToken이 없어도 댓글 목록 조회가 가능하도록 하기
+ 조회하는 게시글에 작성된 모든 댓글을 response에 포함하기

5. 댓글 작성 API
+ AccessToken이 있고, 유효한 Token일 때만 댓글 작성이 가능하도록 하기

6. 댓글 수정 API
+ AccessToken이 있고, 유효한 Token이면서 해당 사용자가 작성한 댓글만 수정 가능하도록 하기

7. 댓글 삭제 API 
+ AccessToken이 있고, 유효한 Token이면서 해당 사용자가 작성한 댓글만 삭제 가능하도록 하기

8. 예외처리
+ RefreshToken을 전달하지 않거나 정상 토큰이 아닐 때는 "Token이 유효하지 않습니다" 라는 에러 메시지를 response에 포함하기
+ 데이터베이스에 존재하는 닉네임을 입력한 채 회원가입 버튼을 누른 경우 "중복된 닉네임입니다."라는 에러메시지를 response에 포함하기
+ 비밀번호와 비밀번화 확인 값이 일치하지 않을 때 "비밀번호와 비밀번호 확인이 일치하지 않습니다." 라는 에러 메시지를 response에 포함하기
+ 로그인 시, 전달된 닉네임과 비밀번호 중 맞지 않는 정보가 있다면 "사용자를 찾을 수 없습니다."라는 에러 메시지를 response에 포함하기
+ AccessToken이 있고, 유효한 Token이면서 해당 사용자가 작성한 게시글/댓글이 아닌 경우에는 "작성자만 삭제할 수 있습니다."라는 에러 메세지를 response에 포함하기
