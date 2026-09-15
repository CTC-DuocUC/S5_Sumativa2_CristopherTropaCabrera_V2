[README.md](https://github.com/user-attachments/files/32220621/README.md)
# SpeedFast – Semana 5: Sincronización de Procesos Concurrentes

Actividad Formativa Individual – Desarrollo Orientado a Objetos II

## Contenido del proyecto

```
SpeedFast-Semana5/
└── src/
    ├── EstadoPedido.java   (enum)
    ├── Pedido.java
    ├── ZonaDeCarga.java    (recurso compartido, métodos synchronized)
    ├── Repartidor.java     (implementa Runnable)
    └── Main.java
```

## Cómo se cumplen los requisitos de la actividad

| Requisito | Dónde está implementado |
|---|---|
| Clase `Pedido` con id, direccionEntrega, estado | `Pedido.java` |
| `setEstado(String nuevoEstado)` | `Pedido.java`, valida contra el enum con `EstadoPedido.valueOf()` antes de asignar |
| `enum EstadoPedido` (PENDIENTE, EN_REPARTO, ENTREGADO) | `EstadoPedido.java` |
| `ZonaDeCarga` con `agregarPedido()` y `retirarPedido()` synchronized | `ZonaDeCarga.java` |
| Control de concurrencia para evitar retiro doble | El `LinkedList` interno solo se manipula dentro de métodos `synchronized`, por lo que un único hilo entra a la vez (monitor sobre la instancia) |
| `Repartidor implements Runnable` | `Repartidor.java` |
| Ciclo retirar → EN_REPARTO → sleep → ENTREGADO | `Repartidor.run()` |
| `Main`: ZonaDeCarga + 5 pedidos + 3 repartidores + ExecutorService | `Main.java` |
| Mensaje final "Todos los pedidos han sido entregados correctamente" | `Main.java`, impreso después de `awaitTermination()` |

## Nota sobre un punto del enunciado

Las instrucciones traen dos frases que no calzan del todo entre sí: en "Características del sistema" dice *"Un repartidor solo puede retirar pedidos con estado EN_REPARTO"*, pero el Paso 4 describe la secuencia real: primero se retira el pedido y **después** se cambia su estado a EN_REPARTO. Esta implementación sigue el Paso 4 (que es el que define el comportamiento paso a paso), ya que es el criterio con el que se arma el ejemplo de salida esperada. La exclusividad de cada pedido queda garantizada igual: al estar dentro de un método `synchronized`, ningún otro repartidor puede tomar ese mismo pedido porque ya fue removido de la lista compartida.

## Cómo abrir el proyecto en IntelliJ IDEA

1. **File > New > Project from Existing Sources** y selecciona la carpeta `SpeedFast-Semana5`.
2. Marca `src` como *Sources Root* si no queda marcada automáticamente.
3. Ejecuta `Main.java` (▶ o `Shift+F10`).

## Cómo subirlo a GitHub

1. Crea un repositorio público en GitHub.
2. Sube el contenido de `SpeedFast-Semana5/` dentro de una carpeta llamada **semana 5**.
3. Copia el enlace del repositorio y súbelo al AVA junto con el .zip.

## Compilar y ejecutar por línea de comandos

```bash
cd SpeedFast-Semana5/src
javac -encoding UTF-8 *.java -d ../out
java -cp ../out Main
```

Como la ejecución es concurrente, el orden exacto de las líneas en consola puede variar levemente entre una corrida y otra; eso es esperado y demuestra que los hilos corren en paralelo.
