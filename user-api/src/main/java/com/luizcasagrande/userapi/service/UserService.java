package com.luizcasagrande.userapi.service;

import com.luizcasagrande.userapi.dto.UserDto;
import com.luizcasagrande.userapi.model.User;
import com.luizcasagrande.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDto> getAll() {
        List<User> usuarios = repository.findAll();
        return usuarios.stream()
                .map(UserDto::convert)
                .collect(Collectors.toList());
    }

    public UserDto findById(long id) {
        Optional<User> usuario = repository.findById(id);
        return usuario.map(UserDto::convert).orElse(null);
    }

    public UserDto save(UserDto dto) {
        User user = repository.save(User.convert(dto));
        return UserDto.convert(user);
    }

    public UserDto delete(long id) {
        Optional<User> usuario = repository.findById(id);
        usuario.ifPresent(u -> repository.delete(u));
        return null;
    }

    public UserDto findByCpf(String cpf) {
        User usuario = repository.findByCpf(cpf);
        if (usuario != null) {
            return UserDto.convert(usuario);
        }
        return null;
    }

    public List<UserDto> queryByNome(String nome) {
        List<User> usuarios = repository.queryByNomeLike(nome);
        return usuarios.stream()
                .map(UserDto::convert)
                .collect(Collectors.toList());
    }
}
