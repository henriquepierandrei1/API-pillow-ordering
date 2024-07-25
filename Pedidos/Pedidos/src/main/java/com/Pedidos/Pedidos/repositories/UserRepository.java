package com.Pedidos.Pedidos.repositories;

import com.Pedidos.Pedidos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("Select a.username from User a where a.id = :id")
    String findUsernameById(@Param("id") long idUser);

    @Query("SELECT p.weightProduct FROM Products p WHERE p.id = :id")
    Integer findWeightProductById(@Param("id") long id);
}
