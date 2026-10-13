// PasarelaPSE.java
// Producto concreto: cobra con PSE
public class PasarelaPSE implements PasarelaPago {

    @Override
    public String nombre() {
        return "PSE";
    }

    // Aprueba siempre
    @Override
    public boolean cobrar(double monto) {
        return true;
    }
}
