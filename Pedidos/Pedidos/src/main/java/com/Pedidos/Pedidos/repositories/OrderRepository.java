package com.Pedidos.Pedidos.repositories;

import com.Pedidos.Pedidos.model.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderProducts,Long> {
    void deleteByIdUser(long idUser);
}
