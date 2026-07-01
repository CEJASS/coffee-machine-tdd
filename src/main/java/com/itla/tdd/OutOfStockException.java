package com.itla.tdd;

/**
 * Se lanza cuando la máquina no puede preparar un café por falta
 * de inventario (vasos, café o azúcar).
 */
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String mensaje) {
        super(mensaje);
    }
}
