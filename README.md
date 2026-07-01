# Máquina de Café — TDD + CI/CD

Proyecto de la asignatura: máquina dispensadora de café construida con **Test-Driven
Development (TDD)** en Java + JUnit 5, con un pipeline de **Integración Continua (CI/CD)**
en **GitHub Actions** que compila el proyecto y ejecuta las pruebas en cada `push` y `pull request`.

## Historia de usuario

> **Como** consumidor de café, **deseo** tomar un vaso de café **para** mitigar el sueño.

### Criterios de aceptación
- Seleccionar entre 3 tamaños de vaso: Pequeño (3 Oz), Mediano (5 Oz), Grande (7 Oz).
- Seleccionar la cantidad de azúcar.
- Mostrar un mensaje si no hay vasos, café o azúcar suficientes.

## Estructura

```
coffee-machine-tdd/
├── pom.xml                         # Configuración Maven + JUnit 5
├── .github/workflows/ci-pipeline.yml   # Pipeline CI/CD (GitHub Actions)
├── src/main/java/com/itla/tdd/
│   ├── CupSize.java                # Enum de tamaños (3/5/7 Oz)
│   ├── Coffee.java                 # Café servido (tamaño, onzas, azúcar)
│   ├── CoffeeMachine.java          # Lógica de la máquina + inventario
│   └── OutOfStockException.java    # Excepción por falta de insumos
└── src/test/java/com/itla/tdd/
    └── CoffeeMachineTest.java      # 11 pruebas TDD (JUnit 5)
```

## Cómo ejecutar las pruebas localmente

Requiere JDK 17+ y Maven:

```bash
mvn test          # compila y ejecuta las 11 pruebas
mvn package       # genera el .jar en target/
```

## El pipeline CI/CD

El workflow `.github/workflows/ci-pipeline.yml` ejecuta, en cada `push` y `pull_request`:

1. **Clonar repositorio** — `actions/checkout`.
2. **Configurar entorno** — JDK 17 (Temurin) con caché de Maven.
3. **Compilar proyecto** — `mvn compile`.
4. **Ejecutar pruebas** — `mvn test` (las 11 pruebas de TDD).
5. **Empaquetar** — `mvn package`.
6. **Publicar reporte** — sube los resultados de Surefire como artefacto.

> Nota: a diferencia del workflow de ejemplo del enunciado (que solo hace `echo`),
> este pipeline **compila y ejecuta las pruebas de verdad**. Si una prueba falla,
> el build se marca en rojo. Eso es lo que hace útil a la Integración Continua en TDD.
