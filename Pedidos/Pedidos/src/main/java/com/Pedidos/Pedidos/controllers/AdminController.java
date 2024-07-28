package com.Pedidos.Pedidos.controllers;


import com.Pedidos.Pedidos.model.OrderProducts;
import com.Pedidos.Pedidos.model.User;
import com.Pedidos.Pedidos.repositories.OrderRepository;
import com.Pedidos.Pedidos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/orders")
    public ResponseEntity<List<OrderProducts>> getAllOrdersProducts (){
        List<OrderProducts> listOrders = orderRepository.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(listOrders);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers (){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable(value = "id") long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
}
