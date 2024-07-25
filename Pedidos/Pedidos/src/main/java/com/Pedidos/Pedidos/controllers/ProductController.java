package com.Pedidos.Pedidos.controllers;

import com.Pedidos.Pedidos.dto.ProductsDto;
import com.Pedidos.Pedidos.infra.security.TokenService;
import com.Pedidos.Pedidos.model.Products;
import com.Pedidos.Pedidos.repositories.ProductsRepository;
import com.Pedidos.Pedidos.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsService productsService;

    @PostMapping("/product/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductsDto productsDto){
        Optional<Products> products = this.productsRepository.findById(productsDto.id());
        if (products.isEmpty()){
            Products newProduct = new Products();
            newProduct.setAmountProduct(productsDto.amount());
            newProduct.setSizeProduct(productsDto.size());
            newProduct.setFill(productsDto.fill());
            newProduct.setTypeProduct(productsDto.type());
            newProduct.setWeightProduct(productsDto.weight());
            this.productsRepository.save(newProduct);
        }
        return ResponseEntity.ok("Product Created!");
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "id") long id){
        Optional<Products> products = this.productsRepository.findById(id);
        if (products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        productsRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted!");
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts(){
        List<Products> products = productsService.findAll();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable(value = "id") long id,@RequestBody ProductsDto productsDto){
        Optional<Products> products = this.productsRepository.findById(id);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Products products1 = products.get();
        products1.setWeightProduct(productsDto.weight());
        products1.setTypeProduct(productsDto.type());
        products1.setSizeProduct(productsDto.size());
        products1.setAmountProduct(productsDto.amount());
        products1.setFill(productsDto.fill());
        this.productsRepository.save(products1);
        return ResponseEntity.ok("Updated Product");

    }
}
