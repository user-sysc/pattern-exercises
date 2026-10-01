package domain;

import domain.clase.ClasePersonaje;
import domain.equipo.Arma;
import domain.equipo.Armadura;
import domain.equipo.Escudo;

/**
 * BUILDER - Producto final.
 *
 * Objeto complejo que combina:
 *  - una clase de personaje (Factory Method),
 *  - un equipo de una misma familia (Abstract Factory),
 *  - datos propios (nombre, nivel) y estadisticas calculadas.
 *
 * Es inmutable: el cliente lo construye paso a paso con PersonajeBuilder y
 * DirectorPersonaje, no con un constructor gigante.
 */
public class Personaje {

    private final String nombre;
    private final int nivel;
    private final ClasePersonaje clase;
    private final Arma arma;
    private final Armadura armadura;
    private final Escudo escudo;
    private final int vida;
    private final int mana;
    private final String habilidad;

    /**
     * Constructor de ensamblaje. Uso interno del Builder: el cliente usa
     * PersonajeBuilder + DirectorPersonaje.
     */
    public Personaje(String nombre, int nivel, ClasePersonaje clase, Arma arma,
                     Armadura armadura, Escudo escudo, int vida, int mana, String habilidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.clase = clase;
        this.arma = arma;
        this.armadura = armadura;
        this.escudo = escudo;
        this.vida = vida;
        this.mana = mana;
        this.habilidad = habilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public ClasePersonaje getClase() {
        return clase;
    }

    public Arma getArma() {
        return arma;
    }

    public Armadura getArmadura() {
        return armadura;
    }

    public Escudo getEscudo() {
        return escudo;
    }

    public int getVida() {
        return vida;
    }

    public int getMana() {
        return mana;
    }

    public String getHabilidad() {
        return habilidad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nClase: " + clase.nombre()
                + "\nNivel: " + nivel
                + "\nVida: " + vida
                + "\nMana: " + mana
                + "\nHabilidad: " + habilidad
                + "\nArma: " + (arma == null ? "Ninguna" : arma.nombre() + " (" + arma.danio() + " danio)")
                + "\nArmadura: " + (armadura == null ? "Ninguna" : armadura.nombre() + " (" + armadura.defensa() + " defensa)")
                + "\nEscudo: " + (escudo == null ? "Ninguno" : escudo.nombre() + " (" + escudo.bloqueo() + " bloqueo)");
    }
}
