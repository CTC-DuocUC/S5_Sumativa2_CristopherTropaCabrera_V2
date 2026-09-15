import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        System.out.println("[Zona de carga inicializada]");
        zonaDeCarga.agregarPedido(new Pedido(1, "Santiago Centro"));
        zonaDeCarga.agregarPedido(new Pedido(2, "Providencia"));
        zonaDeCarga.agregarPedido(new Pedido(3, "Ñuñoa"));
        zonaDeCarga.agregarPedido(new Pedido(4, "Recoleta"));
        zonaDeCarga.agregarPedido(new Pedido(5, "Las Condes"));

        Repartidor juan = new Repartidor("Juan", zonaDeCarga);
        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor pedro = new Repartidor("Pedro", zonaDeCarga);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(juan);
        executor.submit(camila);
        executor.submit(pedro);

        // Se cierra la entrada de nuevas tareas y se espera a que las 3 terminen.
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("[Zona de carga vacía]");
        System.out.println("Todos los pedidos han sido entregados correctamente.");
    }
}
