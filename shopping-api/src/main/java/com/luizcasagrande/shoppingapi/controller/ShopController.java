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

    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(@RequestParam("dataInicio")
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                          @RequestParam(name = "dataFim", required = false)
                                          @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim,
                                          @RequestParam(name = "valorMinimo", required = false) Float valorMinimo) {
        return service.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(@RequestParam("dataInicio")
                                         @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
                                         @RequestParam("dataFim")
                                         @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
        return service.getReportByDate(dataInicio, dataFim);
    }
}
