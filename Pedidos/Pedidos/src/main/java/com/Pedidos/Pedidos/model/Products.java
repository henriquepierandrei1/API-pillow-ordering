package com.Pedidos.Pedidos.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "type cannot be blank")
    private String typeProduct;

    @NotBlank(message = "name cannot be blank")
    private String sizeProduct;

    @NotNull
    @Positive
    private int weightProduct;

    @NotNull
    @Positive
    private int amountProduct;

    @NotBlank(message = "fill cannot be blank")
    private String fill;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "type cannot be blank") String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(@NotBlank(message = "type cannot be blank") String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public @NotBlank(message = "name cannot be blank") String getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(@NotBlank(message = "name cannot be blank") String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    @NotNull
    @Positive
    public int getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(@NotNull @Positive int weightProduct) {
        this.weightProduct = weightProduct;
    }

    @NotNull
    @Positive
    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(@NotNull @Positive int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public @NotBlank(message = "fill cannot be blank") String getFill() {
        return fill;
    }

    public void setFill(@NotBlank(message = "fill cannot be blank") String fill) {
        this.fill = fill;
    }
}
