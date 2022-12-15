## Error Log
<br/>

## - ProceedingJoinPoint를 찾지 못하는 경우
build.gradle -> dependencies에 다음을 추가  
```xml
implementation group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.9.1'
```
name 속성이 aspectjrt일 때는 BeanCreationException이 발생
-> weaver: AOP에서 advice를 핵심기능에 적용(Weaving)하는 설정파일)