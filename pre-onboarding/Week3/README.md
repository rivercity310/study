## Gameduo 기업 개인과제
- [과제내용](gameduo.pdf)

---

### Tech Stack & Version
- Language: Kotlin (JVM Target: Java 17)
- Framework: Spring Boot 3.0.2
- Build: Gradle
- DB: PostgreSQL, Redis
- ORM: JPA Hibernate
- Test: JUnit5
- API Spec: Postman
- Tools: Git, IntelliJ Ultimate

---

### ERD
![image](https://user-images.githubusercontent.com/95991654/214871897-c67d2b24-a911-4f04-893e-add2cae3f799.png)

---

### Point

- #### 제공된 Static Data 호출 후 웹 캐싱
  - 외부 API 주소: https://dmpilf5svl7rv.cloudfront.net/assignment/backend/bossRaidData.json
  - 외부 API 호출: Spring Webflux에서 제공하는 WebClient 사용 
  - Spring framework에서 자체 제공하는 Caching 관련 어노테이션을 이용하여 구현
  - @EnableCaching, @Cacheable 등

<br/>

- #### 랭킹 데이터 Redis 캐싱
  - Spring Data JPA + @RedisHash 어노테이션을 통해 구현
  - 레이드 종료 시점마다 캐싱된 데이터 업데이트

<br/>

- #### DTO 적극 활용
  - @JsonInclude 어노테이션을 통해 요구된 필드만 노출
  - 엔티티에 불필요한 컬럼이 많아질수록 애플리케이션 복잡도만 증가시킨다 판단
    - 엔티티 컬럼 수 최소화, DTO에서 필요한 경우에만 계산하도록 설계
  
<br/>

- #### 싱글턴 활용
  - 핵심 로직과 거리가 먼 객체를 굳이 Bean으로 등록하지 않고 싱글턴 객체로 생성
    - NullArgChecker -> 유효성 검사 객체
    - StaticDataAPIParser -> 외부 API 호출 & 파싱 객체

<br/>

- #### 예외 처리 & 유효성 검사
  - 전달된 인자의 null 가능성 체크 후 비즈니스 로직 수행 (NullArgChecker)
  - 유효하지 않은 데이터가 들어온 경우 얼리 리턴 
  - try-catch를 활용하여 발생 가능성이 있는 에러 핸들링 
    - null을 리턴한 경우, Exception이 발생한 경우
    - catch -> id가 -1인 빈 Entity를 생성 후 반환 -> service layer에서 처리

<br/>

- #### 초기 데이터 자동 생성
  - @PostConstruct 어노테이션을 통해 더미 데이터 자동 생성 & 환경 세팅

<br/>

- #### BatchSize
  - 1:N 관계시 적용, 쿼리 수 최소화 목적
  - 지연 초기화 설정된 필드를 설정 개수만큼 미리 초기화 후 쿼리 한번으로 가져옴
  - BatchSize 미적용시 프록시 객체, 호출마다 쿼리가 나감 -> 성능 저하

---
