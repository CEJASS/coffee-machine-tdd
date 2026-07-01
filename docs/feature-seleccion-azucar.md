# Feature: seleccion de azucar

Esta rama documenta la funcionalidad de seleccion de cucharadas de azucar.

- El consumidor elige cuantas cucharadas de azucar quiere (0 o mas).
- La maquina valida que haya azucar suficiente en el azucarero.
- Si no hay azucar suficiente, muestra el mensaje: "No hay suficiente azucar".
- Pedir una cantidad negativa de azucar es invalido (IllegalArgumentException).

Cubierto por las pruebas TDD en CoffeeMachineTest (bloque "Seleccion de azucar").
