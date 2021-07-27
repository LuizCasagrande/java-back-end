package com.luizcasagrande.shoppingapi.dto;

import com.luizcasagrande.shoppingapi.model.Shop;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    @NotNull
    private Float total;
    @NotNull
    private Date data;
    @NotNull
    private List<ItemDTO> itens;

    public static ShopDTO convert(Shop shop) {
        ShopDTO dto = new ShopDTO();
        dto.setUserIdentifier(shop.getUserIdentifier());
        dto.setTotal(shop.getTotal());
        dto.setData(shop.getData());
        dto.setItens(shop.getItens().stream()
                .map(ItemDTO::convert)
                .collect(Collectors.toList()));
        return dto;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }
}
