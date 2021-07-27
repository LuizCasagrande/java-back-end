package com.luizcasagrande.shoppingapi.controller;

import com.luizcasagrande.shoppingapi.dto.ShopDTO;
import com.luizcasagrande.shoppingapi.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService service;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        return service.getAll();
    }

    @GetMapping("/shopping/shop-by-user/{userIdentifier}")
    public List<ShopDTO> getByUser(@PathVariable String userIdentifier) {
        return service.getByUser(userIdentifier);
    }

    @PostMapping("/shopping/shop-by-data")
    public List<ShopDTO> getByData(@RequestBody ShopDTO dto) {
        return service.getByData(dto);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(@Valid @RequestBody ShopDTO dto) {
        return service.save(dto);
    }
}
