// ProcesadorTarjeta.java
// Creador concreto: crea la pasarela de Tarjeta
public class ProcesadorTarjeta extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaTarjeta();
    }
}
