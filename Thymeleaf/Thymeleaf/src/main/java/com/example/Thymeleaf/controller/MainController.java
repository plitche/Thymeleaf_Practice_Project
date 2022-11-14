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

    @RequestMapping("/product/detail/{productSeq}")
    @ResponseBody
    public ProductDto getProductDetail(@PathVariable int productSeq) throws Exception {
        return productService.getProduct(productSeq);
    }

    @RequestMapping("/product/add/page")
    public String addProductPage(Model model, ProductDto productDto) {
        model.addAttribute("productDto", productDto);
        return "/product/addProduct";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam ProductDto productDto,
                             Model model) throws Exception {
        String name = productDto.getProductName();
        int price = productDto.getProductPrice();
        String desc = productDto.getDescription();

        ProductDto addProduct = productService.addProduct(name, price, desc);
        model.addAttribute("addProduct", addProduct);
        return "redirect:/";
    }
}
