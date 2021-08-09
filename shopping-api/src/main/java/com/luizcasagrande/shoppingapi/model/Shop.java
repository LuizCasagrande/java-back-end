package com.luizcasagrande.shoppingapi.model;

import com.luizcasagrande.shoppingclient.dto.ShopDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userIdentifier;
    private Float total;
    private Date data;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> itens;

    public static Shop convert(ShopDTO dto) {
        Shop shop = new Shop();
        shop.setUserIdentifier(dto.getUserIdentifier());
        shop.setTotal(dto.getTotal());
        shop.setData(new Date());
        shop.setItens(dto.getItens().stream()
                .map(Item::convert)
                .collect(Collectors.toList()));
        return shop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
