// PasarelaTarjeta.java
// Producto concreto: cobra con tarjeta
public class PasarelaTarjeta implements PasarelaPago {

    @Override
    public String nombre() {
        return "Tarjeta";
    }

    // Aprueba si el total es menor o igual a 500000
    @Override
    public boolean cobrar(double monto) {
        return monto <= 500000;
    }
}
