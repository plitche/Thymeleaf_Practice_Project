# Thymeleaf_Practice_Project

### Reference or URL
- https://www.thymeleaf.org/doc/articles/thymeleaf3migration.html


### 문법 정리
#### 1.Model에 담겨있는 값을 꺼내는 방법

- div나 h1, span등의 내용으로 출력하는 경우 th:text를 사용  
- html태그가 포함된 내용을 출력할 때는 unescaped text를 출력해 주는 th:utext를 사용  
- "기본값"을 입력하는 이유는 타임리프를 인식하지 못하는 경우에도 정상적으로 보이도록 하기 위함. 즉, 디자이너/퍼블리셔와의 협업을 위한 방식  

```html
  <div th:text="${message}">기본 메시지</div>
  <input type="text" th:value="${message}" value="기본값">
```   

- Model로 넘어온 값을 th:each를 사용
- th:each의 두 번째 파라미터로 stat을 설정해서 반복의 상태를 확인. 두 번째 파라미터를 생략하는 경우 앞의 변수명 + Stat이 Default 값
    - index : 0부터 시작하는 값
    - count : 1부터 시작하는 값
    - current : 현재 객체 정보
    - even : 짝수 번째 데이터 여부
    - odd : 홀수 번째 데이터 여부
    - first : 첫 번째 데이터 여부
    - last : 마지막 데이터 여부

```html
  <tr th:each="product, stat : ${productList}">
    <td th:text="${stat.index+1}"></td>
    <td th:text="${product.productName}"></td>
    <td th:text="${product.productPrice}"></td>
  </tr>
```  

- a태그를 작성할 때는 th:href="@{}" 을 이용하여 작성
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

- th:onclick (location.href)  
  > location.href 앞 뒤로 | 를 작성. |를 앞 뒤로 써주면 +연산자를 사용하지 않아도, 사용한 것과 같은 효과
```html
<button th:onclick="'location.href=\'' + @{/product/add} + '\''">등록</button>
<button th:onclick="|location.href='@{/product/add}'|">등록</button>
```  

- form
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

- Controller Model 값 jsp에서 사용
  > script: <script th:inline="javascript" >,
  > tag: <body th:inline="text">
  
```js
  <script th:inline="javascript">
    let result = [[${result}]]
  </script>
```
  
 

