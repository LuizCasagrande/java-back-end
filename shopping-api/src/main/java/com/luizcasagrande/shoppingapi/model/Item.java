package com.luizcasagrande.shoppingapi.model;

import com.luizcasagrande.shoppingapi.dto.ItemDTO;

import javax.persistence.Embeddable;

@Embeddable
public class Item {

    private String productIdentifier;
    private Float preco;

    public static Item convert(ItemDTO dto) {
        Item item = new Item();
        item.setProductIdentifier(dto.getProductIdentifier());
        item.setPreco(dto.getPreco());
        return item;
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
