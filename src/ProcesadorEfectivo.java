// ProcesadorEfectivo.java
// Creador concreto: crea la pasarela de Efectivo
public class ProcesadorEfectivo extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaEfectivo();
    }
}
