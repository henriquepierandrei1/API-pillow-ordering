package com.Pedidos.Pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Road cannot be blank")
    private String road;

    @NotBlank(message = "Neighborhood cannot be blank")
    private String neighborhood;

    private int number;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private int quantityOrder;

    private String complement;
}
