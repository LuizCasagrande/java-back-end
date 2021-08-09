package com.luizcasagrande.shoppingclient.dto;

import javax.validation.constraints.NotBlank;

public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    private Float preco;

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
