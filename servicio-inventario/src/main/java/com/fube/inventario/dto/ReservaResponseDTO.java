package com.fube.inventario.dto;

import com.fube.inventario.modelos.EstadoReserva;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservaResponseDTO {
    private Long id;
    private Long productoId;
    private Long pedidoId;
    private Integer cantidad;
    private EstadoReserva estado;
}