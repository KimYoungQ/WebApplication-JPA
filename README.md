webapplication-JPA
===

<br/>

Spring boot와 Spring data JPA 기반 웹 애플리케이션입니다.

- view(HTML, CSS, Bootstrap, Thymeleaf)
- backend(Java, Spring framework, H2(in-memory db))
- Spring boot, Spring data JPA
- 단위/통합 테스트 케이스 
- 로그인 기능(Spring Security)
- 공지사항  
    · CRUD  
    · 다중 파일 첨부  
    · 페이징   

<br/>

로컬에서 실행
---
application.properties에서

1. 포트 확인
```
server.port=8090
```

<br/>
2. clone 후 빌드 실행 또는 jar파일 실행

```
./mvnw package
java -jar (jar파일명).jar
```




<br/>

![메인](https://user-images.githubusercontent.com/45932388/111903897-9af68f80-8a87-11eb-8b97-8c80c4c7e581.PNG)

<br/>

![read](https://user-images.githubusercontent.com/45932388/111903898-9c27bc80-8a87-11eb-9957-c61cd9177677.PNG)

<br/>

![캡처](https://user-images.githubusercontent.com/45932388/111903900-9d58e980-8a87-11eb-9a50-e7fc89c058a3.PNG)
