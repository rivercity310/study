# RESTful APIs 개발

---
## Contributors
- 박지현(jipark96): CRUD 기능 구현 담당
- 황승수(rivercity310): README, Test 코드 작성, Refactoring 담당

---

## Tech Stack & Version
- Language: Kotlin, Java 11
- Framework: Spring Boot 2.7.7
- Build: Gradle
- Database: MariaDB/MySQL
- Test: JUnit5
- IDEA: IntelliJ Ultimate
- Tools: Git, Slack, Postman
---

## Git-Flow

- main: 배포 및 최종본 (<b>branching to develop or hotfix</b>)
- develop: 다음 버전을 개발하는 브랜치 (<b>only branching to feature, merging to main</b>)
- feature: 기능 개발 브랜치 (<b>only merging to develop, local repo must be pushed here</b>)
- hotfix: main 브랜치에서 예상치 못한 버그 발생시 긴급 수정을 담당하는 브랜치

---

## Issues & Solve

<br/>

#### ORDER BY vs Sort
- 쿼리문의 ORDER BY의 성능은 안좋기로 유명
- 다양한 기법들이 있지만, 전체 리스트를 가져와서 Business Layer에서 정렬을 수행하였다.

<br/>

#### 잦은 DB 호출은 성능 저하를 일으킨다
- DB 내용이 변경되지도 않았는데 다시 재접근 하는 것은 리소스 낭비
- 가져온 데이터를 Memoization 하고, 변경내역을 가리키는 boolean 변수를 통해 데이터 변경시에만 DB 접근 
- 더미 데이터 200개 기준 전체 리스트를 불러오는 경우, 테스트 결과는 다음과 같다 

##### 첫번째 호출(172ms)과 두번째 호출(3ms)

![Screenshot from 2023-01-02 07-32-12](https://user-images.githubusercontent.com/95991654/210186415-305619b7-175d-4956-92bc-dcab1031e315.png)

<br/>

#### Postman -> 컨트롤러에 json으로 post 요청시 VO 매핑이 안되는 경우
- Postman 설정
  - Headers -> Key: Content-type, VALUE: application/json
  - Body -> raw, JSON 선택
- Controller VO 파라미터
  - @RequestBody 어노테이션 추가: JSON -> VO 자동 매핑 (deserialize)

<br/>

#### jdbcTemplate DI Error (NullPointerException)
- 주생성자 안에서 초기화: 부생성자, init 메서드 내 초기화시 NPE
  - ex) open class Repo(@Autowired val jdbcTemplate: JdbcTemplate)

---

## APIs
- 전체 채용공고 리스트: GET {host}/api/getBoardList.do
- 채용공고 상세보기: GET {host}/api/getBoard.do
- 채용공고 등록: POST {host}/api/insertBoard.do
- 채용공고 삭제: DELETE {host}/api/deleteBoard.do
- 채용공고 수정: PUT {host}/api/updateBoard.do
- 채용공고 검색: GET {host}/api/searchBoard.do
- 채용공고 지원: POST {host}/api/applyBoard.do

<br/>

### ex) 채용공고 상세보기 요청 
- 지원자 목록과 이 회사 정보, 그리고 이 회사가 올린 다른 공고를 응답

![Screenshot from 2023-01-02 05-50-13](https://user-images.githubusercontent.com/95991654/210184250-556bec0b-ba06-4b55-ae61-833b52c820ab.png)

<br/>

---
