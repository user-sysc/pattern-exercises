package despues.domain.builder;

import despues.domain.Computador;

/**
 * Builder concreto: arma un PC de oficina.
 * Mismos pasos que el gamer, pero otras partes -> otra representacion.
 */
public class ComputadorOficinaBuilder implements ComputadorBuilder {

    private String cpu;
    private int ram;
    private String gpu;
    private String almacenamiento;
    private String fuente;
    private boolean wifi;
    private boolean bluetooth;
    private boolean refrigeracionLiquida;
    private String sistemaOperativo;

    @Override
    public void construirCpu() {
        this.cpu = "Intel Core i5";
    }

    @Override
    public void construirRam() {
        this.ram = 16;
    }

    @Override
    public void construirGpu() {
        this.gpu = "Integrada";
    }

    @Override
    public void construirAlmacenamiento() {
        this.almacenamiento = "512GB SSD";
    }

    @Override
    public void construirFuente() {
        this.fuente = "500W";
    }

    @Override
    public void configurarConectividad() {
        this.wifi = true;
        this.bluetooth = false;
    }

    @Override
    public void configurarRefrigeracion() {
        this.refrigeracionLiquida = false;
    }

    @Override
    public void configurarSoftware() {
        this.sistemaOperativo = "Windows 11";
    }

    @Override
    public Computador getResultado() {
        return new Computador(cpu, ram, gpu, almacenamiento, fuente,
                wifi, bluetooth, refrigeracionLiquida, sistemaOperativo);
    }
}
