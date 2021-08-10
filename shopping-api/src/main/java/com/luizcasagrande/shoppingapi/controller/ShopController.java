package com.luizcasagrande.shoppingapi.controller;

import com.luizcasagrande.shoppingapi.service.ShopService;
import com.luizcasagrande.shoppingclient.dto.ShopDTO;
import com.luizcasagrande.shoppingclient.dto.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopping/shop-by-user/{userIdentifier}")
    public List<ShopDTO> getByUser(@PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @PostMapping("/shopping/shop-by-data")
    public List<ShopDTO> getByData(@RequestBody ShopDTO dto) {
        return shopService.getByData(dto);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(@Valid @RequestBody ShopDTO dto, @RequestHeader("key") String key) {
        return shopService.save(dto, key);
    }

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(@RequestParam("dataInicio")
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                          @RequestParam(name = "dataFim", required = false)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
                                          @RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {
        return shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(@RequestParam("dataInicio")
                                         @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                         @RequestParam("dataFim")
                                         @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
        return shopService.getReportByDate(dataInicio, dataFim);
    }
}
