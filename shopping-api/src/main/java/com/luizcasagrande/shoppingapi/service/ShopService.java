package com.luizcasagrande.shoppingapi.service;

import com.luizcasagrande.shoppingapi.dto.ItemDTO;
import com.luizcasagrande.shoppingapi.dto.ShopDTO;
import com.luizcasagrande.shoppingapi.model.Shop;
import com.luizcasagrande.shoppingapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;

    public List<ShopDTO> getAll() {
        List<Shop> compras = repository.findAll();
        return compras.stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> compras = repository.findByUserIdentifier(userIdentifier);
        return compras.stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByData(ShopDTO dto) {
        List<Shop> compras = repository.findByDataGreaterThan(dto.getData());
        return compras.stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long productId) {
        Optional<Shop> compra = repository.findById(productId);
        return compra.map(ShopDTO::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO dto) {
        dto.setTotal(dto.getItens().stream()
                .map(ItemDTO::getPreco)
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(dto);
        shop = repository.save(shop);
        return ShopDTO.convert(shop);
    }
}
