package com.luizcasagrande.userapi.converter;

import com.luizcasagrande.shoppingclient.dto.UserDTO;
import com.luizcasagrande.userapi.model.User;

public class DTOConverter {

    public static UserDTO convert(User user) {
        UserDTO dto = new UserDTO();
        dto.setNome(user.getNome());
        dto.setCpf(user.getCpf());
        dto.setEndereco(user.getEndereco());
        dto.setEmail(user.getEmail());
        dto.setTelefone(user.getTelefone());
        dto.setDataCadastro(user.getDataCadastro());
        dto.setKey(user.getKey());
        return dto;
    }
}
