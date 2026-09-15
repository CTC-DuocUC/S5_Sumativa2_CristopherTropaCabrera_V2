import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaDeCarga zonaDeCarga;
    private final Random random = new Random();

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        Pedido pedido;

        // Mientras queden pedidos en la zona de carga, este repartidor sigue tomando.
        while ((pedido = zonaDeCarga.retirarPedido()) != null) {
            try {
                System.out.println("[Repartidor - " + nombre + "] Retirando pedido #" + pedido.getId() + "...");
                pedido.setEstado(EstadoPedido.EN_REPARTO.name());
                System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

                System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + "...");
                Thread.sleep(1000 + random.nextInt(2000));

                pedido.setEstado(EstadoPedido.ENTREGADO.name());
                System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

            } catch (InterruptedException e) {
                // Si el hilo es interrumpido durante la entrega, se corta el ciclo con orden.
                System.out.println("[Repartidor - " + nombre + "] Entrega interrumpida.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("[Repartidor - " + nombre + "] No quedan más pedidos, finaliza su turno.");
    }
}
