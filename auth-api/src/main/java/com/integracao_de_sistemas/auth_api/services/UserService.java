package com.integracao_de_sistemas.auth_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integracao_de_sistemas.auth_api.DTOs.UserDTO;
import com.integracao_de_sistemas.auth_api.models.User;
import com.integracao_de_sistemas.auth_api.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User validateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

