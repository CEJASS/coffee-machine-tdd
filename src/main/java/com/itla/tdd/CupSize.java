package com.itla.tdd;

/**
 * Tamaños de vaso disponibles en la máquina de café.
 * Cada tamaño define cuántas onzas (Oz) de café requiere.
 *
 *  PEQUENO -> 3 Oz
 *  MEDIANO -> 5 Oz
 *  GRANDE  -> 7 Oz
 */
public enum CupSize {
    PEQUENO(3),
    MEDIANO(5),
    GRANDE(7);

    private final int onzas;

    CupSize(int onzas) {
        this.onzas = onzas;
    }

    public int getOnzas() {
        return onzas;
    }
}
