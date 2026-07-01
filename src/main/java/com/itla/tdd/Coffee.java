package com.itla.tdd;

import java.util.Objects;

/**
 * Representa un café ya servido: su tamaño, las onzas que contiene
 * y las cucharadas de azúcar que se le agregaron.
 */
public class Coffee {
    private final CupSize tamano;
    private final int onzas;
    private final int cucharadasAzucar;

    public Coffee(CupSize tamano, int cucharadasAzucar) {
        this.tamano = tamano;
        this.onzas = tamano.getOnzas();
        this.cucharadasAzucar = cucharadasAzucar;
    }

    public CupSize getTamano() {
        return tamano;
    }

    public int getOnzas() {
        return onzas;
    }

    public int getCucharadasAzucar() {
        return cucharadasAzucar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee)) return false;
        Coffee coffee = (Coffee) o;
        return onzas == coffee.onzas
                && cucharadasAzucar == coffee.cucharadasAzucar
                && tamano == coffee.tamano;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tamano, onzas, cucharadasAzucar);
    }

    @Override
    public String toString() {
        return "Café " + tamano + " (" + onzas + " Oz), "
                + cucharadasAzucar + " cucharada(s) de azúcar";
    }
}
