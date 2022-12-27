## Log
<br/>

### - ProceedingJoinPoint를 찾지 못하는 경우
```xml
implementation group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.9.1'
```
name 속성이 aspectjrt일 때는 BeanCreationException이 발생
-> weaver: AOP에서 advice를 핵심기능에 적용(Weaving)하는 설정파일)

<br/><hr/>

### - Spring MySQL (JdbcTemplate)
1. build.gradle 설정 

```xml
implementation 'mysql:mysql-connector-java'
implementation 'org.apache.commons:commons-dbcp2:2.9.0'
```

<br/>
2. resources 아래 application.properties 설정

```xml
server.port={port}
spring.datasource.url=jdbc:mysql://localhost:3306/{Schema}?autoReconnect=true
spring.datasource.username={id}
spring.datasource.password={password}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

<br/>
3. applicationContext.xml 설정 (BasicDataSource)

```xml
    <context:property-placeholder location="classpath:application.properties" />
    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${spring.datasource.driver-class-name}" />
        <property name="url" value="${spring.datasource.url}" />
        <property name="username" value="${spring.datasource.username}" />
        <property name="password" value="${spring.datasource.password}" />
    </bean>
```
<br/>
위 설정이 정확하게 되지 않으면 Spring Application이 실행되지 않는다. <br/>
(Error - Failed to configure a DataSource)


<br/><hr/>

### - 브라우저로 jsp 요청시 jsp 파일을 다운로드하는 문제
```xml
implementation group: 'org.apache.tomcat.embed', name: 'tomcat-embed-jasper', version: '9.0.44'
```
build.gradle에 다음을 추가 후 리로딩하기 <br/>
특정 톰캣 최신 버전에서는 jsp 파일을 다운로드 하려고 시도한다.. 따라서 톰캣 버전을 구버전으로 변경

<br/><hr/>

### - Spring JSP 404 
그냥 웬만하면 jsp 쓰지 말자... 


#### jsp 파일 위치 <br/>
src/main/webapp/WEB-INF/views   (없으면 폴더 생성)


#### application.properties 설정
```xml
spring.mvc.view.prifix=/WEB-INF/views
spring.mvc.view.suffix=.jsp
```


#### ViewResolver
```java
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getViewResolver() {
        System.out.println("ViewResolver 실행");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
```

#### 그래도 안된다면..
메인 폴더에서 터미널 -> ./gradlew bootRun (터미널 직접 실행) <br/>
80%에서 멈추는 현상이 있는데 이미 빌드 다 된거임
