package com.Pedidos.Pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idUser;

    private List<Long> idProducts;

    private List<Integer> amount;


    private Date dateOrder;


    private String dateDelivery;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public List<Long> getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(List<Long> idProducts) {
        this.idProducts = idProducts;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }
}
