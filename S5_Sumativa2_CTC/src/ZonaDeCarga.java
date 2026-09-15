import java.util.LinkedList;

// Recurso compartido entre los repartidores. Los métodos son synchronized
// para que solo un hilo a la vez pueda tocar la lista interna.
public class ZonaDeCarga {

    private final LinkedList<Pedido> pedidosPendientes = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
    }

    // Retira el primer pedido disponible. Si no queda ninguno, retorna null.
    public synchronized Pedido retirarPedido() {
        if (pedidosPendientes.isEmpty()) {
            return null;
        }
        return pedidosPendientes.removeFirst();
    }
}
