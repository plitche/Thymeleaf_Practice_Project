
# Thymeleaf_Practice_Project

### Indroduction
java 라이브러리로 웹 및 독립된 환경 모두를 위한 템플릿 엔진으로써,  
확장성이 매우 높고 Spring Framework 용 모듈 및 플러그인 할 수 있는 기능을 제공하므로 최신 HTML5 JVM 웹 개발에 이상적.  
  
HTML에 데이터를 맵핑하여 하나의 View를 Generate한다는 개념과 HTML 친화적인 문법을 사용하기 때문에  
Front-End 개발자와 Back-End 개발자 간의 업무 효율성이 높아져 협업이 쉽게 이루어질 수 있습니다.  
  
기본적으로 Thymeleaf는 HTML, XML, TEXT, JAVASCRIPT, CSS, RAW 6가지 템플릿을 처리할 수 있으며, 각 템플릿을 템플릿 모드라고 함  

### Reference or URL
- https://www.thymeleaf.org/doc/articles/thymeleaf3migration.html


### 문법 정리
#### 기본 문법
```text
  1) 변수 : ${...} - ${member.id}
  2) 선택자 : *{...} - *{id}
  3) 메시지 : #{...} - #{id}
  4) 링크URL : @{...} - @{https://plitche.tistory.com}
  5) 부분적 표현 : ~{...} -
  6) 조건 연산자 : and, or, not, !
    6-1) ${member.age} > 20 and ${member.age} < 10 처럼 각각 분리하여서 사용
    6-2) ${member.age > 20 or member.age < 10} 처럼 한 번에 묶어서 사용
  7) 텍스트 결합 : ${member.id}+${member.name}
  8) 문장 결합 : |아이디 : ${member.id}, 이름 : ${member.name} | - | 로 전체 문장을 묶어줌
  9) if-then : if ? then - ${member.age < 20} ? '미성년자'
  10) if-then-else : if ? then : else - ${member.age < 20} ? '미성년자' : '성인'
  11) default : value ?: defaultValue
```

#### 1.Model에 담겨있는 값을 꺼내는 방법

- div나 h1, span등의 내용으로 출력하는 경우 th:text를 사용  
- html태그가 포함된 내용을 출력할 때는 unescaped text를 출력해 주는 th:utext를 사용  
- "기본값"을 입력하는 이유는 타임리프를 인식하지 못하는 경우에도 정상적으로 보이도록 하기 위함. 즉, 디자이너/퍼블리셔와의 협업을 위한 방식  

```html
  <div th:text="${message}">기본 메시지</div>
  <input type="text" th:value="${message}" value="기본값">
```   

#### 2.Model로 넘어온 값을 th:each를 사용
- th:each의 두 번째 파라미터로 stat을 설정해서 반복의 상태를 확인. 두 번째 파라미터를 생략하는 경우 앞의 변수명 + Stat이 Default 값
    - index : 현재 인덱스(0부터 시작)
    - count : 현재 인덱스(1부터 시작)
    - size : 전체 개수
    - current : 현재 요소
    - even : 현재 반복이 짝수인지(boolean)
    - odd : 현재 반복이 홀수인지(boolean)
    - first : 현재 반복이 첫번째인지(boolean)
    - last : 현재 반복이 마지막인지(boolean)

```html
  <tr th:each="product, stat : ${productList}">
    <td th:text="${stat.index+1}"></td>
    <td th:text="${product.productName}"></td>
    <td th:text="${product.productPrice}"></td>
  </tr>
```  

#### 3.th:fragment
  - <head>태그에 해당 속성을 사용해서 fragment의 이름을 지정.  
  - fragment는 다른 HTML에서 include 또는 replace 속성을 사용해서 적용
  
#### 4.th:block
  - layoutL:fragment 속성에 이름을 지정해서 실제 Content 페이지의 내용을 채우는 기능
  - 해당 기능은 동적(Dynamic)인 처리가 필요할 때 사용
  
#### 5.th:replace
  - JSP의 <include> 태그와 유사한 속성
  - th:fragment을 통해 설정한 이름을 찾아 해당 코드로 치환

#### 6.a태그를 작성할 때는 th:href="@{}" 을 이용하여 작성
```html
  <!-- 특정 url로 이동 -->
  <a th:href="@{https://plitche.tistory.com}">블로그 이동</a>
  <!-- 현재 서버 내에서 이동 -->
  <a th:href="@{/product/list}">상품리스트 페이지</a>
  <!-- 파라미터를 넘길 시 -->
  <a th:href="@{/product/detail(id = ${product.seq})}">상품 상세 페이지</a>
  <!-- 파라미터를 여러 개 넘길 시 -->
  <a th:href="@{/product/detail(id = ${product.seq}, productName = ${product.name}})}">상품 상세 페이지</a>
  <!-- PathVariable 사용 시 -->
  <a th:href="@{/product/detail/{productSeq}(id = ${product.seq})}">상품 상세 페이지</a>
```

#### 7.th:onclick (location.href)  
  > location.href 앞 뒤로 | 를 작성. |를 앞 뒤로 써주면 +연산자를 사용하지 않아도, 사용한 것과 같은 효과
```html
<button th:onclick="'location.href=\'' + @{/product/add} + '\''">등록</button>
<button th:onclick="|location.href='@{/product/add}'|">등록</button>
```  

#### 8.form
  - th:action
    > form data를 보낼 url를 설정
  - th:object
    > form data를 담을 객체를 설정
  - th:field
    > 담는 객체의 필드을 동적으로 매핑 (사용시 객체의 필드로 id과 name이 자동생성)


```html
@GetMapping("/writeBbs")
public String writeBbs(Model model, ProductDto productDto){
    // 먼저 form태그를 사용할 페이지에 비어있는 객체를 전달해야함
    model.addAttribute("productDto",productDto);
    return "writeBbs";
}
```  
  
```html
<form th:action="@{/add/product}"  th:object="${productDto}" method="post" >
  <table class="table">
      <tbody>
      <tr>
          <td>이름</td>
          <td><input type="text" th:field="*{productName}"></td>
      </tr>
      <tr>
          <td>가격</td>
          <td><input type="text" th:field="*{productPrice}"></td>
      </tr>
      </tbody>
      <button type="submit">등록</button>
      // th:field에서는 받아온 객체의 필드변수명을 입력해야 form data를 보낼때 자동으로 매핑
  </table>
</form>
```

```html
    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute ProductDto productDto, Model model) throws Exception {
        // @ModelAttribute를 사용 혹은 생략 가능

        String name = productDto.getProductName();
        int price = productDto.getProductPrice();
        String desc = productDto.getDescription();

        ProductDto addProduct = productService.addProduct(name, price, desc);
        model.addAttribute("addProduct", addProduct);
        return "redirect:/";
    }
```

#### 9.Controller Model 값 jsp에서 사용
  > script: <script th:inline="javascript" >,
  > tag: <body th:inline="text">
  
```js
  <script th:inline="javascript">
    let result = [[${result}]]
  </script>
```
  
#### 10. th:value
  - 엘리먼트들의 value값을 지정  
  
```html
  <button th:value=”${hello}”/>  
```

#### 11. th:with
  - 변수 값을 지정해서 사용
  
```html
  <div th:with=”temp=${hello}” th:text=”${temp}”>
```
  
#### 12. th:switch
  - th:case에서 case문을 다루고 *로 case문에서 다루지 않은 모든 경우가 처리
  > java switch문의 default역할
    
```html
  <div th:switch="${hello}">
    <p th:case="'admin'">User is an administrator
    <p th:case="#{roles.manager}">User is a manager
    <p th:case="*">User is a manager
  </div>
```

#### 13. th:if
    - 조건문을 사용할 때 else 대신 unless를 사용
    - if문의 조건식과 unless의 조건식을 동일하게 해야 함
    
```html
  <span th:if="${member.age >= 20}">
    성인입니다.
  </span>
  <span th:unless="${student.age >= 20}">
    청소년입니다.
  </span> 
```
   
    
Thymeleaf를 사용하는 방법은 4가지가 있습니다. 변수식으로 사용하는 ${}와 메세지방식 #{}, 객체변수식인 *{}, 링크방식 @{}이 있습니다.
```java
 import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
    
@RequestMapping("/")
@Controller
public class WebController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
} 
```  
    
위 코드는 URL맵핑으로 컨트롤러입니다. JSP때 많으하는 MVC패턴이죠. 거기서 C, 컨트롤러입니다. 스프링에서는 어노테이션이라는것이 들어가게 되는데 클래스를 만들고 @Controller라고 명시하면 이는 컨트롤러가 됩니다. RestController도 있습니다.

@RequestMapping("/")은 주소창에서 /라는 요청이 왔을 때 실행하겠다는 의미입니다. 만약 @RequestMapping("/index")라고 하면 주소창에서 /index가 왔을 때 해당 요청을 처리하겠죠. 

지금있는 코드로 /index요청이 오면 index를 띄우는것으로 되어 있습니다. 여기에 메소드 하나를 만듭니다.
