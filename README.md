# Simulador de Parqueadero

## Versión
**1.0**

## Descripción
Sistema de simulación de parqueadero desarrollado en Java. Permite controlar ingresos y salidas de vehículos, gestionar espacios disponibles, generar tickets y reportes estadísticos.

## Alcance

### ✅ Qué hace
- Registrar ingreso de vehículos (Carro, Moto, Camión, Bicicleta)
- Registrar salida y calcular tarifa
- Control de espacios por piso y tipo de vehículo
- Generación de tickets con ID único
- Cálculo de tarifa según horas de estadía
- Reportes completos y por tipo de vehículo
- Historial de tickets
- Resumen de ocupación por piso

### ❌ Qué NO hace
- No tiene interfaz gráfica (solo consola)
- No persiste datos en base de datos real

## Tarifas por Hora

| Tipo de Vehículo | Tarifa por Hora | Espacios que ocupa |
|------------------|-----------------|-------------------|
| Carro | $1,000 | 1 espacio |
| Moto | $500 | 1 espacio |
| Camión | $2,000 | 2 espacios |
| Bicicleta | $200 | 1 espacio |

## Distribución de Espacios por Piso

| Tipo | Cantidad |
|------|----------|
| Carro | 10 espacios |
| Moto | 5 espacios |
| Camión | 3 espacios |
| Bicicleta | 2 espacios |

## Documentación Javadoc
[https://mpfx.github.io/SimuladorParqueadero/](https://mpfx.github.io/SimuladorParqueadero/)

## Tecnologías
- Java
- Javadoc
- GitHub Pages

## Modo de uso
**Este proyecto NO es una aplicación visual/gráfica.**
Funciona exclusivamente por consola (CLI - Command Line Interface).

## Propósito
**Educativo / Pedagógico**
- Programación orientada a objetos en Java
- Herencia y clases abstractas
- Interfaces
- Documentación técnica con Javadoc
- Control de versiones con Git y GitHub Pages

## Derechos de autor
**© 2026 ISC Israel de Jesus Mar Parada**
Todos los derechos reservados.
