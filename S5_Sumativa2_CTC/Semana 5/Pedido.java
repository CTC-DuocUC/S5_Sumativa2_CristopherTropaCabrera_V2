public class Pedido {

    private int id;
    private String direccionEntrega;
    private String estado;

    public Pedido(int id, String direccionEntrega) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE.name();
    }

    public int getId() {
        return id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    // Recibe un String para respetar la firma pedida, pero se valida contra
    // el enum antes de asignar, así se evita un estado mal escrito.
    public void setEstado(String nuevoEstado) {
        EstadoPedido estadoValidado = EstadoPedido.valueOf(nuevoEstado);
        this.estado = estadoValidado.name();
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " [direccion=" + direccionEntrega + ", estado=" + estado + "]";
    }
}
