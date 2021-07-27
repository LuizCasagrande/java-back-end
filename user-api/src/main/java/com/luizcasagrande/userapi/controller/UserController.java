package com.luizcasagrande.userapi.controller;

import com.luizcasagrande.userapi.dto.UserDto;
import com.luizcasagrande.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user")
    public List<UserDto> getUsers() {
        return service.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/user")
    public UserDto newUser(@RequestBody UserDto dto) {
        return service.save(dto);
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDto findByCpf(@PathVariable String cpf) {
        return service.findByCpf(cpf);
    }

    @DeleteMapping("/user/{id}")
    public UserDto delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDto> queryByNome(@RequestParam("nome") String nome) {
        return service.queryByNome(nome);
    }
}
