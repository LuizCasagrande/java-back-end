package com.luizcasagrande.userapi.controller;

import com.luizcasagrande.shoppingclient.dto.UserDTO;
import com.luizcasagrande.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<UserDTO> getUsers() {
        return service.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/user")
    public UserDTO newUser(@RequestBody UserDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDTO findByCpf(@PathVariable String cpf) {
        return service.findByCpf(cpf);
    }

    @DeleteMapping("/user/{id}")
    public UserDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByNome(@RequestParam("nome") String nome) {
        return service.queryByNome(nome);
    }
}
