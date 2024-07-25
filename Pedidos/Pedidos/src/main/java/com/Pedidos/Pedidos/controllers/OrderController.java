package com.Pedidos.Pedidos.controllers;


import com.Pedidos.Pedidos.dto.OrderDto;
import com.Pedidos.Pedidos.dto.UserDto;
import com.Pedidos.Pedidos.model.OrderProducts;
import com.Pedidos.Pedidos.model.User;
import com.Pedidos.Pedidos.repositories.OrderRepository;
import com.Pedidos.Pedidos.repositories.ProductsRepository;
import com.Pedidos.Pedidos.repositories.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  ProductsRepository productsRepository;


    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {
        OrderProducts order = new OrderProducts();
        order.setDateOrder(new Date());
        order.setAmount(orderDto.amount());
        order.setDateDelivery(orderDto.dateDelivery());
        order.setIdProducts(orderDto.idProducts());
        order.setIdUser(user.getId());
        user.setQuantityOrder(user.getQuantityOrder()+1);



        for (int i = 0; i < orderDto.idProducts().size(); i++){
            if (productsRepository.findById(orderDto.idProducts().get(i)).isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product isn't exists!");
            }
        }
        if (user.getQuantityOrder()>5){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exceeded order limit!");
        }

        orderRepository.save(order);
        userRepository.save(user);
        System.out.println(user.getQuantityOrder());

        return ResponseEntity.status(HttpStatus.CREATED).body("Order Created");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") long id,@AuthenticationPrincipal User user){
        Optional<OrderProducts> order = this.orderRepository.findById(id);
        if (order.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        user.setQuantityOrder(user.getQuantityOrder()-1);
        userRepository.save(user);
        orderRepository.deleteById(id);
        System.out.println(user.getQuantityOrder());

        return ResponseEntity.ok("Order Deleted!");

    }
}
