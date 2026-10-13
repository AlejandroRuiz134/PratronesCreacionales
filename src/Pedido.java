// Pedido.java
import java.util.ArrayList;
import java.util.List;

// BUILDER: el Pedido es el producto que se arma paso a paso
// PROTOTYPE: ademas se puede clonar para repetir un pedido
public class Pedido {

    private final String id;
    private final String cliente;
    private final TipoEntrega tipoEntrega;
    private final String direccion;
    private final List<ItemPedido> items;
    private final String notas;
    private final int cupon;
    private final double propina;

    // Constructor privado: la unica forma de obtener un Pedido es el Builder
    private Pedido(Builder builder, String id) {
        this.id = id;
        this.cliente = builder.cliente;
        this.tipoEntrega = builder.tipoEntrega;
        this.direccion = builder.direccion;
        this.items = builder.items;
        this.notas = builder.notas;
        this.cupon = builder.cupon;
        this.propina = builder.propina;
    }

    // PROTOTYPE: constructor privado que copia los datos de otro pedido
    private Pedido(Pedido original, String id) {
        this.id = id;
        this.cliente = original.cliente;
        this.tipoEntrega = original.tipoEntrega;
        this.direccion = original.direccion;
        // Se crea una lista nueva para no compartir la del original
        this.items = new ArrayList<>(original.items);
        this.notas = original.notas;
        // El cupon no se copia, es de un solo uso
        this.cupon = 0;
        this.propina = original.propina;
    }

    public String getId() {
        return id;
    }

    // PROTOTYPE: devuelve una copia del pedido con un numero nuevo
    public Pedido clonar() {
        String nuevoId = GeneradorConsecutivo.obtenerInstancia().siguiente();
        return new Pedido(this, nuevoId);
    }

    // Agrega un item al pedido (se usa para modificar el clon)
    public void agregarItem(ItemPedido item) {
        items.add(item);
    }

    // total = subtotal * (100 - cupon) / 100 + propina
    public double calcularTotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal = subtotal + item.getSubtotal();
        }
        return subtotal * (100 - cupon) / 100 + propina;
    }

    // Imprime el pedido en consola
    public void mostrarResumen() {
        String entrega = "" + tipoEntrega;
        if (tipoEntrega == TipoEntrega.DOMICILIO) {
            entrega = entrega + " (" + direccion + ")";
        }
        System.out.println("Pedido " + id + " | Cliente: " + cliente + " | Entrega: " + entrega);

        for (ItemPedido item : items) {
            System.out.println("  - " + item.getCantidad() + " x " + item.getNombre()
                    + " ($" + (int) item.getPrecioUnitario() + ")");
        }

        if (!notas.isEmpty()) {
            System.out.println("  Notas: " + notas);
        }

        System.out.println("  Cupon: " + cupon + "% | Propina: $" + (int) propina
                + " | Total: $" + (int) calcularTotal());
    }

    // Builder: guarda los datos del pedido mientras se va configurando
    public static class Builder {

        // Valores por defecto
        private String cliente = "";
        private TipoEntrega tipoEntrega = TipoEntrega.RECOGER;
        private String direccion = "";
        private List<ItemPedido> items = new ArrayList<>();
        private String notas = "";
        private int cupon = 0;
        private double propina = 0;

        // Cada metodo devuelve el propio Builder para poder encadenar

        public Builder conCliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder conTipoEntrega(TipoEntrega tipoEntrega) {
            this.tipoEntrega = tipoEntrega;
            return this;
        }

        public Builder conDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder agregarItem(ItemPedido item) {
            this.items.add(item);
            return this;
        }

        public Builder conNotas(String notas) {
            this.notas = notas;
            return this;
        }

        public Builder conCupon(int cupon) {
            if (cupon < 0 || cupon > 100) {
                throw new IllegalArgumentException("El cupon debe estar entre 0 y 100");
            }
            this.cupon = cupon;
            return this;
        }

        public Builder conPropina(double propina) {
            this.propina = propina;
            return this;
        }

        // Valida y, solo si todo esta bien, pide el numero y crea el Pedido
        public Pedido construir() {
            if (cliente.isEmpty()) {
                throw new IllegalStateException("El cliente es obligatorio");
            }
            if (items.isEmpty()) {
                throw new IllegalStateException("El pedido debe tener al menos un item");
            }
            if (tipoEntrega == TipoEntrega.DOMICILIO && direccion.isEmpty()) {
                throw new IllegalStateException("El domicilio requiere direccion");
            }

            // El numero se pide despues de validar: un pedido rechazado no consume numero
            String id = GeneradorConsecutivo.obtenerInstancia().siguiente();
            return new Pedido(this, id);
        }
    }
}
