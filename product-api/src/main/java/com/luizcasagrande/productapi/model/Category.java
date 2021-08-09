package com.luizcasagrande.productapi.model;

import com.luizcasagrande.shoppingclient.dto.CategoryDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    public static Category convert(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setNome(dto.getNome());
        return category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
