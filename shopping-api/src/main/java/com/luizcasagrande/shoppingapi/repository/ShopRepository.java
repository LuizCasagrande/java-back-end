package com.luizcasagrande.shoppingapi.repository;

import com.luizcasagrande.shoppingapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository {

    List<Shop> findByUserIdentifier(String userIdentifier);

    List<Shop> findByTotalGreaterThan(Float total);

    List<Shop> findByDataGreaterThan(Date data);
}
