package com.luizcasagrande.userapi.service;

import com.luizcasagrande.shoppingclient.dto.UserDTO;
import com.luizcasagrande.userapi.converter.DTOConverter;
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

    public List<UserDTO> getAll() {
        List<User> usuarios = repository.findAll();
        return usuarios.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        Optional<User> usuario = repository.findById(id);
        return usuario.map(DTOConverter::convert).orElse(null);
    }

    public UserDTO save(UserDTO dto) {
        User user = repository.save(User.convert(dto));
        return DTOConverter.convert(user);
    }

    public UserDTO delete(long id) {
        Optional<User> usuario = repository.findById(id);
        usuario.ifPresent(u -> repository.delete(u));
        return null;
    }

    public UserDTO findByCpf(String cpf) {
        User usuario = repository.findByCpf(cpf);
        if (usuario != null) {
            return DTOConverter.convert(usuario);
        }
        return null;
    }

    public List<UserDTO> queryByNome(String nome) {
        List<User> usuarios = repository.queryByNomeLike(nome);
        return usuarios.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
}
