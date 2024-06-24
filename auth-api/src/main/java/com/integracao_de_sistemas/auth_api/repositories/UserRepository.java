package com.integracao_de_sistemas.auth_api.repositories;

import com.integracao_de_sistemas.auth_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
