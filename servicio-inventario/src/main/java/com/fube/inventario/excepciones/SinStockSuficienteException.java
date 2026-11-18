package com.fube.inventario.excepciones;

public class SinStockSuficienteException extends RuntimeException {
    public SinStockSuficienteException(String mensaje) {
        super(mensaje);
    }
}