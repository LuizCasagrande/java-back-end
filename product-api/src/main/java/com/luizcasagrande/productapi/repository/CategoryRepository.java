package com.luizcasagrande.productapi.repository;

import com.luizcasagrande.productapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
