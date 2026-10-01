package despues.domain.builder;

import despues.domain.Computador;

/**
 * Builder concreto: arma un PC de alto rendimiento.
 * Implementa cada paso con los valores de esta representacion.
 */
public class ComputadorGamerBuilder implements ComputadorBuilder {

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
        this.cpu = "Intel Core i9";
    }

    @Override
    public void construirRam() {
        this.ram = 32;
    }

    @Override
    public void construirGpu() {
        this.gpu = "RTX 4070";
    }

    @Override
    public void construirAlmacenamiento() {
        this.almacenamiento = "1TB SSD NVMe";
    }

    @Override
    public void construirFuente() {
        this.fuente = "750W 80+ Gold";
    }

    @Override
    public void configurarConectividad() {
        this.wifi = true;
        this.bluetooth = true;
    }

    @Override
    public void configurarRefrigeracion() {
        this.refrigeracionLiquida = true;
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
