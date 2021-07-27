package com.luizcasagrande.productapi.repository;

import com.luizcasagrande.productapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductByCategoryId(long categoryId);

    Product findByProductIdentifier(String productIdentifier);
}
