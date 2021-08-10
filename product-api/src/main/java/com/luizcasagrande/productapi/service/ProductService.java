package com.luizcasagrande.productapi.service;

import com.luizcasagrande.productapi.converter.DTOConverter;
import com.luizcasagrande.productapi.model.Product;
import com.luizcasagrande.productapi.repository.CategoryRepository;
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
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> produtos = productRepository.findAll();
        return produtos.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(long categoryId) {
        List<Product> produtos = productRepository.getProductByCategoryId(categoryId);
        return produtos.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product produto = productRepository.findByProductIdentifier(productIdentifier);
        if (produto == null) {
            throw new ProductNotFoundException();
        }
        return DTOConverter.convert(produto);
    }

    public ProductDTO save(ProductDTO dto) {
        boolean existsCategory = categoryRepository.existsById(dto.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product produto = productRepository.save(Product.convert(dto));
        return DTOConverter.convert(produto);
    }

    public void delete(long id) {
        Optional<Product> produto = productRepository.findById(id);
        produto.ifPresentOrElse(p -> productRepository.delete(p), () -> {
            throw new ProductNotFoundException();
        });
    }
}
