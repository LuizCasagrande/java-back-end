package com.luizcasagrande.userapi.controller;

import com.luizcasagrande.shoppingclient.dto.UserDTO;
import com.luizcasagrande.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/user")
    public UserDTO newUser(@RequestBody UserDTO dto) {
        return userService.save(dto);
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDTO findByCpfAndKey(@PathVariable String cpf, @RequestParam("key") String key) {
        return userService.findByCpfAndKey(cpf, key);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByNome(@RequestParam("nome") String nome) {
        return userService.queryByNome(nome);
    }
}
