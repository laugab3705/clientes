package com.fube.inventario.controladores;
import com.fube.inventario.dto.ReservaRequestDTO;
import com.fube.inventario.dto.ReservaResponseDTO;
import com.fube.inventario.servicios.InventarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaControlador {

    private final InventarioServicio inventarioServicio;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> reservarStock(@RequestBody ReservaRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioServicio.reservarStock(request));
    }

    @DeleteMapping("/pedido/{pedidoId}")
    public ResponseEntity<Void> liberarReservaPorPedido(@PathVariable("pedidoId") Long pedidoId) {
        inventarioServicio.liberarReservaPorPedido(pedidoId);
        return ResponseEntity.noContent().build();
    }
}
