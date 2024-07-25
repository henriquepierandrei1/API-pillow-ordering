package com.Pedidos.Pedidos.services;

import com.Pedidos.Pedidos.model.Products;
import com.Pedidos.Pedidos.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;


    public List<Products> findAll() {
        List<Products> products = productsRepository.findAll();
        return products;
    }
}
