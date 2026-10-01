package antes.domain;

/**
 * Producto del lado ANTES: sin Builder.
 *
 * Para cubrir las combinaciones de partes se usan constructores telescopicos
 * (uno por cada cantidad de parametros). El constructor final tiene 9
 * parametros, varios de ellos booleanos sin nombre: al leer una llamada nadie
 * sabe que significa cada true/false.
 *
 * Problemas:
 *  - Dificil de leer: los argumentos son posicionales.
 *  - Facil equivocarse: cambiar el orden compila pero cambia el resultado.
 *  - Muchos parametros opcionales obligan a sobrecargar constructores.
 *  - Agregar un atributo nuevo obliga a crear mas constructores y tocar las
 *    llamadas existentes.
 */
public class Computador {

    private final String cpu;
    private final int ram;
    private final String gpu;
    private final String almacenamiento;
    private final String fuente;
    private final boolean wifi;
    private final boolean bluetooth;
    private final boolean refrigeracionLiquida;
    private final String sistemaOperativo;

    public Computador(String cpu) {
        this(cpu, 8);
    }

    public Computador(String cpu, int ram) {
        this(cpu, ram, "Integrada");
    }

    public Computador(String cpu, int ram, String gpu) {
        this(cpu, ram, gpu, "256GB SSD");
    }

    public Computador(String cpu, int ram, String gpu, String almacenamiento) {
        this(cpu, ram, gpu, almacenamiento, "500W");
    }

    public Computador(String cpu, int ram, String gpu, String almacenamiento, String fuente) {
        this(cpu, ram, gpu, almacenamiento, fuente, false, false, false, "Linux");
    }

    public Computador(String cpu, int ram, String gpu, String almacenamiento, String fuente,
                      boolean wifi, boolean bluetooth, boolean refrigeracionLiquida,
                      String sistemaOperativo) {
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.almacenamiento = almacenamiento;
        this.fuente = fuente;
        this.wifi = wifi;
        this.bluetooth = bluetooth;
        this.refrigeracionLiquida = refrigeracionLiquida;
        this.sistemaOperativo = sistemaOperativo;
    }

    @Override
    public String toString() {
        return "CPU: " + cpu
                + "\nRAM: " + ram + " GB"
                + "\nGPU: " + gpu
                + "\nAlmacenamiento: " + almacenamiento
                + "\nFuente: " + fuente
                + "\nWiFi: " + (wifi ? "Si" : "No")
                + "\nBluetooth: " + (bluetooth ? "Si" : "No")
                + "\nRefrigeracion liquida: " + (refrigeracionLiquida ? "Si" : "No")
                + "\nSistema operativo: " + sistemaOperativo;
    }
}
