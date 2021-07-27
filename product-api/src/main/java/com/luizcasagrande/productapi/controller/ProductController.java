package com.luizcasagrande.productapi.controller;

import com.luizcasagrande.productapi.dto.ProductDTO;
import com.luizcasagrande.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/product")
    public List<ProductDTO> getProducts() {
        return service.getAll();
    }

    @GetMapping("/product/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
        return service.getProductByCategoryId(categoryId);
    }

    @GetMapping("/product/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier) {
        return service.findByProductIdentifier(productIdentifier);
    }

    @PostMapping("/product")
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO dto) {
        return service.save(dto);
    }

    @DeleteMapping("/product/{id}")
    public ProductDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
