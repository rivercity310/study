# ThingsFlow

----

## Contributors
- **김동열** : 무한 스크롤, 예외처리, README 작성
- **조정빈** : 게시글 수정/삭제
- **황승수** : 게시글 등록
---
## Contents
- [Tech & Stack](#tech--stack)
- [Summary](#summary)
- [Feature List](#feature-list)
- [ERD](#erd)
- [TEST-CODE](#test-code)
- [Issue](#issue)
---

## Tech & Stack
### language
<img src="https://img.shields.io/badge/Java-17-007396?style=fflat&logo=java&logoColor=white"><Br>

### Framework & API
<img src="https://img.shields.io/badge/Spring Boot-2.7.7-6DB33F?style=flat&logo=Spring Boot&logoColor=white"/><br>
<img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=spring&logoColor=white"><br>
<img src="https://img.shields.io/badge/gradle-02303A?style=flat&logo=gradle&logoColor=white"><Br>
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=flat&logo=Spring security&logoColor=white"><br>
<img src="https://img.shields.io/badge/Swagger-6DB33F?style=flat&logo=Swagger&logoColor=white">

### DB
<img src="https://img.shields.io/badge/PostgreSQL-4169E1?style=flat&logo=PostgreSQL&logoColor=white"><br>

### ORM
<img src="https://img.shields.io/badge/JPA-6DB33F?style=flat&logo=&logoColor=white"/><br>

### Tools
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat&logo=IntelliJ IDEA&logoColor=white"><br>

### Communication
<img src="https://img.shields.io/badge/Slack-4A154B?style=flat&logo=Slack&logoColor=white"><br>
<img src="https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white"><br>
---
## Summary
- 사용자는 게시글을 등록, 수정, 삭제가 가능
- 등록할 때 비밀번호를 설정하며 이는 수정 및 삭제에 검증 용도로 사용
- 비밀번호는 6자리 이상이어야 하며, 숫자 1개 이상 필수 포함

---
## Feature List
### Simple List
- 사용자는 게시글을 **등록**할 수 있음
    - 게시글은 **제목**과 **본문**으로 구성
    - 제목(최대 20자), 본문(최대 200자)로 서버에서 제한
    - 제목과 본문 모두 **이모지**가 포함 가능
- 사용자는 게시글을 올릴 때 **비밀번호**를 설정 가능. 추후, 본인이 작성한 게시물에 비밀번호 입력 후 **수정**, **삭제** 가능
    - 회원가입, 로그인 없이 **비밀번호만** 일치하면 수정, 삭제 가능
    - 비밀번호는 DB에 **암호화**된 형태로 저장
    - 비밀번호는 **6자 이상**이어야 하고, **숫자 1개**이상 **반드시** 포함
- 모든 사용자는 한 페이지 내에서 모든 게시글을 **최신 순**으로 확인
- (우선) 외부 API를 활용하여 사용자가 게시글을 업로드한 시점의 날씨(예: 맑음, 흐림, 소나기, 눈 등) 정보가 게시글에 포함
    - 외부 API는 자유, https://www.weatherapi.com 에 가입 후 Real-time Weather API 사용하시는 것을 추천
    - 발급 받으신 API Key 는 전달해주지 않아도 됨
    - 게시글 작성 시 자동으로 데이터베이스에 추가되고, 수정은 불가
- 게시글의 개수가 많을 때, 사용자가 앱이나 웹에서 스크롤을 내릴 때마다 오래된 글들이
  계속 로드 되는 형태로 API 를 수정 [무한 스크롤]
  - 게시글이 중복으로 나타나면 안됨
  - 추가 로드는 20 개 단위


### Condition
1. DBMS 는 PostgreSQL, MySQL, SQLite 중 하나를 사용
2. 프론트엔드는 직접 구현하지 않아도 됨
3. 함께 일할 프론트엔드 개발자가 API 스펙을 보고 문제 없이 개발할 수 있어야 함
4. ORM 을 사용

### API Specification
머지 후 작성


---
## ERD
![image](https://user-images.githubusercontent.com/95991654/211572573-82fe7604-6e1c-4f0e-aab0-a8ea03c593ee.png)
---
## Test-Code


---
## Issue
> org.springframework.context.ApplicationContextException: Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException
- 원인 
  - Spring Boot 2.6버전 이후에 요청 경로를 ControllerHandler 에 매칭시키기 위한 것이 작동.
  - spring.mvc.pathmatch.matching-strategy 기본 값이 ant_path_matcher 에서 path_pattern_parser 로 변경되어 있음
- 해결
  - application.properties 에 추가
  > spring.mvc.pathmatch.matching-strategy=ant_path_matcher
---
> Spring Security 를 사용하고 Client 에서 Controller 로 Mapping 이 막히는 이슈
- Spring Security 란,
  - Spring 기반 애플리케이션의 보안(인증과 권한)을 담당하는 프레임워크
  - Filter 기반으로 동작
  - Filter 는 Dispatcher Servlet 으로 가기전에 적용되므로 가장 먼저 URL 의 요청을 받지만, Interceptor 는 Dispatcher 와 Controller 사이에 위치
  - Spring MVC Request Lifecycle <br>
    ![image](https://user-images.githubusercontent.com/95991654/211572653-44522ea8-af23-4218-9a53-2889db6afdb8.png)  - Authentication Architecture <br>
    ![image](https://user-images.githubusercontent.com/95991654/211572718-92c3adc0-9225-4d0f-8390-be804a88bdec.png)- 원인
  - 로그인 등 인증 수단이 없기에 Filter 에서 인가받지 않은 사용자로 분류되어 Dispatcher Servlet 으로 접근 불가
- 해결
  1. 접근을 허용하는 경로를 설정
  2. 인증 로직 구현
---
> CreationTimeStamp Annotation VS CreatedDate Annotation
- CreationTimeStamp Annotation 
  - Hibernate 에서 제공하는 어노테이션
  - 데이터베이스로 넘어갈 때 자동으로 입력
  - milliseconds 단위까지 입력 가능
- CreatedDate
  - JPA 에서 제공하는 어노테이션
  - Spring Auditing 기술을 사용 
    - JPA 는 JPA 고유 메모리 공간(context)을 이용해서 엔티티 객체들을 관리
    - 관리되는 객체들이 변경되면 데이터에 반영되는 형식 -> 재사용하는 방식
    - 객체의 생성, 수정 등 변화를 감지하고 감지되면 자동 입력
    - microseconds 단위까지 입력 가능
- CreationTimeStamp 대신 CreatedDate 를 지향함.
  - CreationTimeStamp -> CreatedDate
  - UpdateTimestamp -> LastModifiedDate
---
> 수정하는 작업에서 repository.save(entity) 를 실행할 때 오류 발생. <br>
> 오류 내용 : 해당 entity 가 존재하기 때문에 저장할 수 없음 <- 대충 이런 느낌...
- 영속성 컨텍스트
  - 엔티티를 영구적으로 저장하는 환경
  - 애플리케이션 DB 사이에서 객체를 보관하는 일종의 가상 DB 역할
  - 따라서, Entity Manager 를 통해 저장하거나 조회하면 Entity Manager 는 영속성 컨텍스트 Entity 를 보관 및 관리
  - repository.update(entity)를 하지 않고 객체의 일부분만 바꿔줘도 영속성 컨텍스트로 인해 update 가 자동으로 수행

![image](https://user-images.githubusercontent.com/95991654/211572780-8231205c-c2fc-4ac6-b8bd-9bc59b0c5dd7.png)---
