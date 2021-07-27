package com.luizcasagrande.shoppingapi.dto;

import com.luizcasagrande.shoppingapi.model.Item;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float preco;

    public static ItemDTO convert(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setProductIdentifier(item.getProductIdentifier());
        dto.setPreco(item.getPreco());
        return dto;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
