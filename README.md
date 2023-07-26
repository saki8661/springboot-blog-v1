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
