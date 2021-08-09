package com.luizcasagrande.productapi.service;

import com.luizcasagrande.productapi.converter.DTOConverter;
import com.luizcasagrande.productapi.model.Product;
import com.luizcasagrande.productapi.repository.ProductRepository;
import com.luizcasagrande.shoppingclient.dto.ProductDTO;
import com.luizcasagrande.shoppingclient.exception.CategoryNotFoundException;
import com.luizcasagrande.shoppingclient.exception.ProductNotFoundException;
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
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(long categoryId) {
        List<Product> produtos = repository.getProductByCategoryId(categoryId);
        return produtos.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product produto = repository.findByProductIdentifier(productIdentifier);
        if (produto == null) {
            throw new ProductNotFoundException();
        }
        return DTOConverter.convert(produto);
    }

    public ProductDTO save(ProductDTO dto) {
        boolean existsCategory = repository.existsById(dto.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product produto = repository.save(Product.convert(dto));
        return DTOConverter.convert(produto);
    }

    public void delete(long id) {
        Optional<Product> produto = repository.findById(id);
        produto.ifPresentOrElse(p -> repository.delete(p), () -> {
            throw new ProductNotFoundException();
        });
    }
}
