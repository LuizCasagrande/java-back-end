package com.luizcasagrande.userapi.service;

import com.luizcasagrande.shoppingclient.dto.UserDTO;
import com.luizcasagrande.shoppingclient.exception.UserNotFoundException;
import com.luizcasagrande.userapi.converter.DTOConverter;
import com.luizcasagrande.userapi.model.User;
import com.luizcasagrande.userapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        Optional<User> usuario = userRepository.findById(id);
        return usuario.map(DTOConverter::convert).orElse(null);
    }

    public UserDTO save(UserDTO dto) {
        dto.setKey(UUID.randomUUID().toString());
        User user = userRepository.save(User.convert(dto));
        return DTOConverter.convert(user);
    }

    public void delete(long id) {
        Optional<User> usuario = userRepository.findById(id);
        usuario.ifPresentOrElse(u -> userRepository.delete(u), () -> {
            throw new UserNotFoundException();
        });
    }

    public UserDTO findByCpfAndKey(String cpf, String key) {
        User usuario = userRepository.findByCpfAndKey(cpf, key);
        if (usuario != null) {
            return DTOConverter.convert(usuario);
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByNome(String nome) {
        List<User> usuarios = userRepository.queryByNomeLike(nome);
        return usuarios.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
}
