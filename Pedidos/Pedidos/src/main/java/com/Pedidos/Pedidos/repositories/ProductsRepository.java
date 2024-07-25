package com.Pedidos.Pedidos.repositories;

import com.Pedidos.Pedidos.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products,Long> {
}
