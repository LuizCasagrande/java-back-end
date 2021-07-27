package com.luizcasagrande.productapi.service;

import com.luizcasagrande.productapi.dto.ProductDTO;
import com.luizcasagrande.productapi.model.Product;
import com.luizcasagrande.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> getAll() {
        List<Product> produtos = repository.findAll();
        return produtos.stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(long categoryId) {
        List<Product> produtos = repository.getProductByCategoryId(categoryId);
        return produtos.stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product produto = repository.findByProductIdentifier(productIdentifier);
        if (produto != null) {
            return ProductDTO.convert(produto);
        }
        return null;
    }

    public ProductDTO save(ProductDTO dto) {
        Product produto = repository.save(Product.convert(dto));
        return ProductDTO.convert(produto);
    }

    public ProductDTO delete(long id) {
        Optional<Product> produto = repository.findById(id);
        produto.ifPresent(p -> repository.delete(p));
        return null;
    }
}
