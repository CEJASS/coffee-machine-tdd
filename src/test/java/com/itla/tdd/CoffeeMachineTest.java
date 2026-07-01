package com.itla.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas TDD de la máquina de café.
 * Organizadas por criterio de aceptación de la historia de usuario.
 */
@DisplayName("Máquina de café - TDD")
class CoffeeMachineTest {

    @Nested
    @DisplayName("Selección de tamaño de vaso")
    class SeleccionDeTamano {

        @Test
        @DisplayName("Un vaso pequeño sirve 3 Oz de café")
        void vasoPequenoSirve3Onzas() {
            CoffeeMachine maquina = new CoffeeMachine(10, 100, 10);

            Coffee cafe = maquina.prepararCafe(CupSize.PEQUENO, 0);

            assertEquals(3, cafe.getOnzas());
            assertEquals(CupSize.PEQUENO, cafe.getTamano());
        }

        @Test
        @DisplayName("Un vaso mediano sirve 5 Oz de café")
        void vasoMedianoSirve5Onzas() {
            CoffeeMachine maquina = new CoffeeMachine(10, 100, 10);

            Coffee cafe = maquina.prepararCafe(CupSize.MEDIANO, 0);

            assertEquals(5, cafe.getOnzas());
        }

        @Test
        @DisplayName("Un vaso grande sirve 7 Oz de café")
        void vasoGrandeSirve7Onzas() {
            CoffeeMachine maquina = new CoffeeMachine(10, 100, 10);

            Coffee cafe = maquina.prepararCafe(CupSize.GRANDE, 0);

            assertEquals(7, cafe.getOnzas());
        }
    }

    @Nested
    @DisplayName("Selección de azúcar")
    class SeleccionDeAzucar {

        @Test
        @DisplayName("El café conserva las cucharadas de azúcar seleccionadas")
        void cafeConElAzucarSeleccionado() {
            CoffeeMachine maquina = new CoffeeMachine(10, 100, 10);

            Coffee cafe = maquina.prepararCafe(CupSize.MEDIANO, 2);

            assertEquals(2, cafe.getCucharadasAzucar());
        }

        @Test
        @DisplayName("Se puede pedir café sin azúcar")
        void cafeSinAzucar() {
            CoffeeMachine maquina = new CoffeeMachine(10, 100, 10);

            Coffee cafe = maquina.prepararCafe(CupSize.PEQUENO, 0);

            assertEquals(0, cafe.getCucharadasAzucar());
        }

        @Test
        @DisplayName("Pedir azúcar negativa es inválido")
        void azucarNegativaEsInvalida() {
            CoffeeMachine maquina = new CoffeeMachine(10, 100, 10);

            assertThrows(IllegalArgumentException.class,
                    () -> maquina.prepararCafe(CupSize.PEQUENO, -1));
        }
    }

    @Nested
    @DisplayName("Manejo de inventario y mensajes de error")
    class Inventario {

        @Test
        @DisplayName("Sin vasos muestra 'No hay vasos disponibles'")
        void sinVasosLanzaMensaje() {
            CoffeeMachine maquina = new CoffeeMachine(0, 100, 10);

            OutOfStockException ex = assertThrows(OutOfStockException.class,
                    () -> maquina.prepararCafe(CupSize.PEQUENO, 1));
            assertEquals("No hay vasos disponibles", ex.getMessage());
        }

        @Test
        @DisplayName("Sin café suficiente muestra 'No hay suficiente café'")
        void sinCafeLanzaMensaje() {
            // 2 Oz no alcanzan para un vaso pequeño (3 Oz).
            CoffeeMachine maquina = new CoffeeMachine(5, 2, 10);

            OutOfStockException ex = assertThrows(OutOfStockException.class,
                    () -> maquina.prepararCafe(CupSize.PEQUENO, 1));
            assertEquals("No hay suficiente café", ex.getMessage());
        }

        @Test
        @DisplayName("Sin azúcar suficiente muestra 'No hay suficiente azúcar'")
        void sinAzucarLanzaMensaje() {
            CoffeeMachine maquina = new CoffeeMachine(5, 100, 1);

            OutOfStockException ex = assertThrows(OutOfStockException.class,
                    () -> maquina.prepararCafe(CupSize.GRANDE, 2));
            assertEquals("No hay suficiente azúcar", ex.getMessage());
        }
    }

    @Nested
    @DisplayName("Descuento de inventario al servir")
    class DescuentoDeInventario {

        @Test
        @DisplayName("Servir un café descuenta vaso, café y azúcar")
        void servirDescuentaInventario() {
            CoffeeMachine maquina = new CoffeeMachine(2, 10, 4);

            maquina.prepararCafe(CupSize.MEDIANO, 2); // -1 vaso, -5 Oz, -2 azúcar

            assertEquals(1, maquina.getVasos());
            assertEquals(5, maquina.getOnzasDeCafe());
            assertEquals(2, maquina.getCucharadasDeAzucar());
        }

        @Test
        @DisplayName("La máquina sirve cafés hasta agotar el inventario")
        void sirveHastaAgotarInventario() {
            // Alcanza para exactamente 2 vasos pequeños (3 Oz c/u = 6 Oz).
            CoffeeMachine maquina = new CoffeeMachine(2, 6, 10);

            maquina.prepararCafe(CupSize.PEQUENO, 0);
            maquina.prepararCafe(CupSize.PEQUENO, 0);

            OutOfStockException ex = assertThrows(OutOfStockException.class,
                    () -> maquina.prepararCafe(CupSize.PEQUENO, 0));
            assertEquals("No hay vasos disponibles", ex.getMessage());
        }
    }
}
