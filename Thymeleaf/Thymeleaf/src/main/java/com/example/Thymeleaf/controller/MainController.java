package com.example.Thymeleaf.controller;

import com.example.Thymeleaf.dto.ProductDto;
import com.example.Thymeleaf.service.MemberService;
import com.example.Thymeleaf.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MainController {

    final MemberService memberService;
    final ProductService productService;

    @RequestMapping("/")
    public String getMain(Model model) throws Exception {
        model.addAttribute("productList", productService.getProductList());
        return "main";
    }

    @RequestMapping("/product/add/page")
    public String addProductPage(Model model, ProductDto productDto) {
        model.addAttribute("productDto", productDto);
        return "product/addProduct";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProduct(ProductDto productDto, Model model) throws Exception {
        // Parameter productDto를 받을때 @RequestParam annotation을 사용하면 에러 발생
        // @ModelAttribute를 사용해야 한다.

        String name = productDto.getProductName();
        int price = productDto.getProductPrice();
        String desc = productDto.getDescription();

        ProductDto addProduct = productService.addProduct(name, price, desc);
        model.addAttribute("addProduct", addProduct);
        return "redirect:/";
    }

    @RequestMapping(value = "/product/detail/{seq}")
    public String productDetail(@PathVariable int seq) {
        System.out.println("seq = " + seq);
        return null;
    }

}
