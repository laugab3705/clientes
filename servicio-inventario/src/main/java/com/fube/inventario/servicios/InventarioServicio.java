package com.fube.inventario.servicios;

import com.fube.inventario.dto.*;
import com.fube.inventario.excepciones.*;
import com.fube.inventario.modelos.*;
import com.fube.inventario.repositorio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioServicio {

    private final ProductoRepositorio productoRepositorio;
    private final ReservaRepositorio reservaRepositorio;

    @Transactional(readOnly = true)
    public ProductoStockDTO consultarStock(Long productoId) {
        Producto producto = productoRepositorio.findById(productoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con ID: " + productoId));

        return ProductoStockDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .cantidadDisponible(producto.getCantidadDisponible())
                .build();
    }

    @Transactional
    public ReservaResponseDTO reservarStock(ReservaRequestDTO request) {
        Producto producto = productoRepositorio.findById(request.getProductoId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con ID: " + request.getProductoId()));

        if (producto.getCantidadDisponible() < request.getCantidad()) {
            throw new SinStockSuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        producto.setCantidadDisponible(producto.getCantidadDisponible() - request.getCantidad());
        productoRepositorio.save(producto);

        Reserva reserva = Reserva.builder()
                .producto(producto)
                .pedidoId(request.getPedidoId())
                .cantidad(request.getCantidad())
                .estado(EstadoReserva.ACTIVA)
                .build();

        Reserva reservaGuardada = reservaRepositorio.save(reserva);

        return ReservaResponseDTO.builder()
                .id(reservaGuardada.getId())
                .productoId(producto.getId())
                .pedidoId(reservaGuardada.getPedidoId())
                .cantidad(reservaGuardada.getCantidad())
                .estado(reservaGuardada.getEstado())
                .build();
    }

    @Transactional
    public void liberarReservaPorPedido(Long pedidoId) {
        List<Reserva> reservas = reservaRepositorio.findByPedidoId(pedidoId);

        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == EstadoReserva.ACTIVA) {
                Producto producto = reserva.getProducto();
                producto.setCantidadDisponible(producto.getCantidadDisponible() + reserva.getCantidad());
                productoRepositorio.save(producto);

                reserva.setEstado(EstadoReserva.LIBERADA);
                reservaRepositorio.save(reserva);
            }
        }
    }
}