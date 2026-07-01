package com.itla.tdd;

/**
 * Máquina dispensadora de café.
 *
 * Equipo (inventario):
 *   - Vasos      : cantidad de vasos disponibles.
 *   - Cafetera   : onzas de café disponibles.
 *   - Azucarero  : cucharadas de azúcar disponibles.
 *
 * Historia de usuario:
 *   Como consumidor de café, deseo tomar un vaso de café para mitigar el sueño.
 *
 * Criterios de aceptación:
 *   - Poder seleccionar entre 3 tamaños de vaso (Pequeño, Mediano, Grande).
 *   - Poder seleccionar la cantidad de azúcar.
 *   - Mostrar un mensaje si no hay vasos, café o azúcar suficientes.
 */
public class CoffeeMachine {

    private int vasos;
    private int onzasDeCafe;
    private int cucharadasDeAzucar;

    public CoffeeMachine(int vasos, int onzasDeCafe, int cucharadasDeAzucar) {
        this.vasos = vasos;
        this.onzasDeCafe = onzasDeCafe;
        this.cucharadasDeAzucar = cucharadasDeAzucar;
    }

    /**
     * Prepara un café del tamaño indicado con la cantidad de azúcar solicitada.
     * Descuenta el inventario correspondiente cuando hay existencias suficientes.
     *
     * @throws IllegalArgumentException si las cucharadas de azúcar son negativas.
     * @throws OutOfStockException      si falta algún insumo.
     */
    public Coffee prepararCafe(CupSize tamano, int cucharadasAzucarPedidas) {
        if (cucharadasAzucarPedidas < 0) {
            throw new IllegalArgumentException("Las cucharadas de azúcar no pueden ser negativas");
        }
        if (vasos <= 0) {
            throw new OutOfStockException("No hay vasos disponibles");
        }
        if (onzasDeCafe < tamano.getOnzas()) {
            throw new OutOfStockException("No hay suficiente café");
        }
        if (cucharadasDeAzucar < cucharadasAzucarPedidas) {
            throw new OutOfStockException("No hay suficiente azúcar");
        }

        // Descontar inventario.
        vasos--;
        onzasDeCafe -= tamano.getOnzas();
        cucharadasDeAzucar -= cucharadasAzucarPedidas;

        return new Coffee(tamano, cucharadasAzucarPedidas);
    }

    public int getVasos() {
        return vasos;
    }

    public int getOnzasDeCafe() {
        return onzasDeCafe;
    }

    public int getCucharadasDeAzucar() {
        return cucharadasDeAzucar;
    }
}
