package com.luizcasagrande.productapi.controller;

import com.luizcasagrande.productapi.service.ProductService;
import com.luizcasagrande.shoppingclient.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/product/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/product/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping("/product")
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO dto) {
        return productService.save(dto);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
