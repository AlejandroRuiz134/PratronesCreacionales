// ProcesadorPSE.java
// Creador concreto: crea la pasarela de PSE
public class ProcesadorPSE extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaPSE();
    }
}
