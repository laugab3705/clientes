package com.fube.inventario.controladores;

import com.fube.inventario.dto.ProductoStockDTO;
import com.fube.inventario.servicios.InventarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoControlador {

    private final InventarioServicio inventarioServicio;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoStockDTO> consultarStock(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inventarioServicio.consultarStock(id));
    }
}
