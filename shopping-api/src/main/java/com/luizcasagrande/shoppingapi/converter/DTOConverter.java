package com.luizcasagrande.shoppingapi.converter;

import com.luizcasagrande.shoppingapi.model.Item;
import com.luizcasagrande.shoppingapi.model.Shop;
import com.luizcasagrande.shoppingclient.dto.ItemDTO;
import com.luizcasagrande.shoppingclient.dto.ShopDTO;

import java.util.stream.Collectors;

public class DTOConverter {

    public static ItemDTO convert(Item item) {
        ItemDTO dto = new ItemDTO();
        dto.setProductIdentifier(item.getProductIdentifier());
        dto.setPreco(item.getPreco());
        return dto;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO dto = new ShopDTO();
        dto.setUserIdentifier(shop.getUserIdentifier());
        dto.setTotal(shop.getTotal());
        dto.setData(shop.getData());
        dto.setItens(shop.getItens().stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList()));
        return dto;
    }
}
