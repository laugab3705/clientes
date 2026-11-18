package com.fube.inventario.dto;

import lombok.Data;

@Data
public class ReservaRequestDTO {
    private Long productoId;
    private Long pedidoId;
    private Integer cantidad;
}
