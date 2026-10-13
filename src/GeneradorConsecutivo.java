// GeneradorConsecutivo.java
// SINGLETON: unica fuente de numeracion de los pedidos
public class GeneradorConsecutivo {

    // La unica instancia que va a existir
    private static GeneradorConsecutivo instancia;

    private int contador = 0;

    // Constructor privado: nadie puede hacer new desde afuera
    private GeneradorConsecutivo() {
        System.out.println("[Consecutivo] Instancia creada.");
    }

    // Punto de acceso: crea la instancia la primera vez y luego devuelve la misma
    public static GeneradorConsecutivo obtenerInstancia() {
        if (instancia == null) {
            instancia = new GeneradorConsecutivo();
        }
        return instancia;
    }

    // Devuelve el siguiente numero con formato PED-0001
    public String siguiente() {
        contador++;
        return String.format("PED-%04d", contador);
    }
}
