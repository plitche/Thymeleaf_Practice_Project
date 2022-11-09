# Thymeleaf_Practice_Project

### Reference or URL
- https://www.thymeleaf.org/doc/articles/thymeleaf3migration.html


### 문법 정리
##### 1.Model에 담겨있는 값을 꺼내는 방법

1. div나 h1, span등의 내용으로 출력하는 경우 th:text를 사용  
2. html태그가 포함된 내용을 출력할 때는 unescaped text를 출력해 주는 th:utext를 사용  
3. "기본값"을 입력하는 이유는 타임리프를 인식하지 못하는 경우에도 정상적으로 보이도록 하기 위함. 즉, 디자이너/퍼블리셔와의 협업을 위한 방식  

```html
  <div th:text="${message}">기본 메시지</div>
  <input type="text" th:value="${message}" value="기본값">
```   


  
