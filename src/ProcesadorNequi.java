// ProcesadorNequi.java
// Creador concreto: crea la pasarela de Nequi (punto 4, R6)
public class ProcesadorNequi extends ProcesadorPago {

    @Override
    protected PasarelaPago crearPasarela() {
        return new PasarelaNequi();
    }
}
