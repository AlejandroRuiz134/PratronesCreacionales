// PasarelaEfectivo.java
// Producto concreto: cobra en efectivo
public class PasarelaEfectivo implements PasarelaPago {

    @Override
    public String nombre() {
        return "Efectivo";
    }

    // Aprueba siempre
    @Override
    public boolean cobrar(double monto) {
        return true;
    }
}
