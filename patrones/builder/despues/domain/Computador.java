package despues.domain;

/**
 * Producto del lado DESPUES.
 *
 * Es inmutable: todos los campos son final. Su unico constructor recibe los
 * valores definitivos, pero NO es la forma en que el cliente lo crea: para eso
 * existen ComputadorBuilder y EnsambladorDirector. El cliente ya no tiene que
 * recordar el orden de 9 parametros.
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

    /**
     * Constructor de ensamblaje. Uso interno del Builder: el cliente construye
     * el objeto paso a paso con ComputadorBuilder + EnsambladorDirector.
     */
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

    public String getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public String getGpu() {
        return gpu;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public String getFuente() {
        return fuente;
    }

    public boolean isWifi() {
        return wifi;
    }

    public boolean isBluetooth() {
        return bluetooth;
    }

    public boolean isRefrigeracionLiquida() {
        return refrigeracionLiquida;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
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
