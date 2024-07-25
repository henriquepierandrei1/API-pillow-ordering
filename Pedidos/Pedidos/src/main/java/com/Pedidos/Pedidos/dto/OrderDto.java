package com.Pedidos.Pedidos.dto;

import com.Pedidos.Pedidos.model.Products;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record OrderDto(List<Integer> amount, List<Long> idProducts, LocalDateTime dateTime, String dateDelivery, long idUser) {

}
