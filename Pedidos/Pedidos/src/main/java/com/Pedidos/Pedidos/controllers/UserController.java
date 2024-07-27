package com.Pedidos.Pedidos.controllers;


import com.Pedidos.Pedidos.repositories.ProductsRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Controlador do User")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("success!");
    }


}
