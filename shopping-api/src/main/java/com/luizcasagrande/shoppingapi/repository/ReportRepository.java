package com.luizcasagrande.shoppingapi.repository;

import com.luizcasagrande.shoppingapi.model.Shop;
import com.luizcasagrande.shoppingclient.dto.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

    ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);
}
