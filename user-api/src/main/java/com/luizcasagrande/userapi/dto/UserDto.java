package com.luizcasagrande.userapi.dto;

import com.luizcasagrande.userapi.model.User;

import java.util.Date;

public class UserDto {

    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private Date dataCadastro;

    public static UserDto convert(User user) {
        UserDto dto = new UserDto();
        dto.setNome(user.getNome());
        dto.setCpf(user.getCpf());
        dto.setEndereco(user.getEndereco());
        dto.setEmail(user.getEmail());
        dto.setTelefone(user.getTelefone());
        dto.setDataCadastro(user.getDataCadastro());
        return dto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
