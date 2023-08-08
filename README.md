# 스프링부트 블로그 만들기

## 기술스택

- Springboot 2.7
- JDK 11
- VSCODE
- MYSQL8.0

## 의존성

- Lombok
- DevTools(저장시 서버 리로드)
- Spring WEB
- JPA
- h2
- MYSQL
- Mustache

## 프로젝트 시작

### 07/25

```sql
create database blogdb;
```

### 07/26

1. index.mustache를 header와 footer로 분리
2. 분리된 index를 layout에 담아 활용
3. 템플릿 user폴더 joinForm, loginForm, updateForm 생성
4. 템플릿 board폴더 saveForm, detailForm 생성
5. Controller 화면구현 확인

### 07/27

1. Web 회원가입 요청(username, password, email)
2. Console에서 응답 뿌리기

   - 메소드를 찾을 수 있어야한다(joinForm)

### 07/28

1. 서블릿과 파싱이해

   - DS에서 파싱 -> Controller
   - HttpServletRequset -> requset객체를 Controller에서 파싱
   - 바디데이터를 버퍼로 받아서 처음부터 파싱

2. 실무에서는 DTO활용

   - 모델링 후에 테이블 생성
   - DB접속하기(핵심기능 구현)

3. 부가로직

   - DB에서 유니크 제약조건 걸기
   - 프론트에서 막기(길이제약)
   - 백엔드에서 막기(null과 empty는 막아야한다)
   - 우회접속막기(에러페이지처리)

4. 더미데이터 만들기

   - 일하기 쉬워진다!!!

### 08/01

1. 회원가입 예외처리

2. LoginDTO

   - 로그인은 POST요청
   - 파일 복사 붙여넣기 할 때
     같은 파일로 인식하는 경우가 있어서 주의!

3. 세션

   - 쿠키와 세션
   - 로그아웃(세션 날리기)

4. WriteDTO

5. 게시글목록보기

   - 페이징 쿼리

### 08/02

1. 페이징 쿼리

   - 페이징 쿼리 복습
   - 페이징 쿼리 last

2. 상세보기

   - 쿼리스트링과 @PathVariable의 차이
   - 권한체크

3. 글삭제

   - @PathVariable 값 받기
   - 인증검사
   - 권한검사
   - 모델에 접근해서 삭제

4. 글수정

   - 수정하기 들어갔을 때 수정전 내용이 화면에 표시 되어야함
   - Controller에 UpdateDTO담아서 뿌리기
   - update, updateForm 차이

### 08/07

1. Ajax통신

   - DOM으로 부터 값 가져오기
   - 통신하기
   - 파싱하기
   - 상태코드 확인

### 08/08

1. Reply

   - 테이블, DTO만들기
   - Controller와 Rrpository연결
   - DB에 insert

2. 게시글 상세보기 - 댓글 리스트 동적쿼리

   - build.gradle QLRM추가
   - DB 조인쿼리 활용
   - Mustache 문법활용

3. 댓글삭제

   - 댓글삭제 후 해당 페이지에 남아있기 구현완료
