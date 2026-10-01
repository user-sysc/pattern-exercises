package antes.controller;

import antes.domain.Computador;

/**
 * Controlador del lado ANTES.
 * Construye los computadores llamando al constructor gigante: al leerlo no se
 * sabe que representa cada parametro y menos aun cada true/false.
 */
public class ComputadorController {

    public void mostrarGamer() {
        Computador pc = new Computador(
                "Intel Core i9", 32, "RTX 4070", "1TB SSD NVMe", "750W 80+ Gold",
                true, true, true, "Windows 11");
        System.out.println("--- PC Gamer ---");
        System.out.println(pc);
    }

    public void mostrarOficina() {
        Computador pc = new Computador(
                "Intel Core i5", 16, "Integrada", "512GB SSD", "500W",
                true, false, false, "Windows 11");
        System.out.println("--- PC de Oficina ---");
        System.out.println(pc);
    }
}
