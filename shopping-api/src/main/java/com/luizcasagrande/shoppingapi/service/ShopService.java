package com.luizcasagrande.shoppingapi.service;

import com.luizcasagrande.shoppingapi.converter.DTOConverter;
import com.luizcasagrande.shoppingapi.model.Shop;
import com.luizcasagrande.shoppingapi.repository.ShopRepository;
import com.luizcasagrande.shoppingclient.dto.ItemDTO;
import com.luizcasagrande.shoppingclient.dto.ShopDTO;
import com.luizcasagrande.shoppingclient.dto.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> compras = repository.findByUserIdentifier(userIdentifier);
        return compras.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByData(ShopDTO dto) {
        List<Shop> compras = repository.findByDataGreaterThan(dto.getData());
        return compras.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long productId) {
        Optional<Shop> compra = repository.findById(productId);
        return compra.map(DTOConverter::convert).orElse(null);
    }

    public ShopDTO save(ShopDTO dto) {
        dto.setTotal(dto.getItens().stream()
                .map(ItemDTO::getPreco)
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(dto);
        shop = repository.save(shop);
        return DTOConverter.convert(shop);
    }

    public List<ShopDTO> getShopsByFilter(Date dataInicio, Date dataFim, Float valorMinimo) {
        List<Shop> compras = repository.getShopByFilters(dataInicio, dataFim, valorMinimo);
        return compras.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());

    }

    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        return repository.getReportByDate(dataInicio, dataFim);
    }
}
