package com.luizcasagrande.productapi.converter;

import com.luizcasagrande.productapi.model.Category;
import com.luizcasagrande.productapi.model.Product;
import com.luizcasagrande.shoppingclient.dto.CategoryDTO;
import com.luizcasagrande.shoppingclient.dto.ProductDTO;

public class DTOConverter {

    public static CategoryDTO convert(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setNome(category.getNome());
        return dto;
    }

    public static ProductDTO convert(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setNome(product.getNome());
        dto.setPreco(product.getPreco());
        dto.setDescricao(product.getDescricao());
        dto.setProductIdentifier(product.getProductIdentifier());
        if (product.getCategory() != null) {
            dto.setCategory(DTOConverter.convert(product.getCategory()));
        }
        return dto;
    }
}
