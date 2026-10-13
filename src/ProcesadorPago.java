// ProcesadorPago.java
// FACTORY METHOD: clase creadora, tiene el flujo de pago escrito una sola vez
public abstract class ProcesadorPago {

    // Factory Method: cada subclase decide que pasarela crear
    protected abstract PasarelaPago crearPasarela();

    // Flujo comun para todos los medios de pago
    public void procesar(Pedido pedido) {
        PasarelaPago pasarela = crearPasarela();
        double total = pedido.calcularTotal();

        System.out.println("Procesando " + pedido.getId() + " con " + pasarela.nombre()
                + " por $" + (int) total);

        boolean aprobado = pasarela.cobrar(total);

        if (aprobado) {
            System.out.println("  -> Pago APROBADO");
        } else {
            System.out.println("  -> Pago RECHAZADO");
        }
    }
}
