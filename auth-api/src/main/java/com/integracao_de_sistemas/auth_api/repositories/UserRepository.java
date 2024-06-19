package com.integracao_de_sistemas.auth_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.integracao_de_sistemas.auth_api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}

