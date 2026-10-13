// PasarelaNequi.java
// Producto concreto: cobra con Nequi (punto 4, R6)
public class PasarelaNequi implements PasarelaPago {

    @Override
    public String nombre() {
        return "Nequi";
    }

    // Aprueba si el total es menor o igual a 300000
    @Override
    public boolean cobrar(double monto) {
        return monto <= 300000;
    }
}
