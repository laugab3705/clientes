package com.fube.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoStockDTO {
    private Long id;
    private String nombre;
    private Integer cantidadDisponible;

    // Getters manuales para asegurar la serialización JSON en Spring
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getCantidadDisponible() { return cantidadDisponible; }
}