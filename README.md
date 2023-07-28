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
