package com.luizcasagrande.shoppingapi.service;

import com.luizcasagrande.shoppingclient.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    public UserDTO getUserByCpf(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/cpf/" + cpf;
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
        return response.getBody();
    }
}
