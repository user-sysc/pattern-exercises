package domain.builder;

import domain.Personaje;
import domain.clase.ClasePersonaje;
import domain.equipo.Arma;
import domain.equipo.Armadura;
import domain.equipo.Escudo;

/**
 * BUILDER - Construye un Personaje paso a paso.
 *
 * Metodos fluidos (encadenables) para fijar cada parte. El metodo build()
 * calcula las estadisticas finales a partir de la clase y el nivel, y recien
 * ahi crea el objeto complejo.
 */
public class PersonajeBuilder {

    private String nombre;
    private int nivel = 1;
    private ClasePersonaje clase;
    private Arma arma;
    private Armadura armadura;
    private Escudo escudo;

    public PersonajeBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PersonajeBuilder nivel(int nivel) {
        this.nivel = nivel;
        return this;
    }

    public PersonajeBuilder clase(ClasePersonaje clase) {
        this.clase = clase;
        return this;
    }

    public PersonajeBuilder arma(Arma arma) {
        this.arma = arma;
        return this;
    }

    public PersonajeBuilder armadura(Armadura armadura) {
        this.armadura = armadura;
        return this;
    }

    public PersonajeBuilder escudo(Escudo escudo) {
        this.escudo = escudo;
        return this;
    }

    public Personaje build() {
        if (clase == null) {
            throw new IllegalStateException("No se puede construir un personaje sin clase");
        }
        int vida = clase.vidaBase() + nivel * 10;
        int mana = clase.manaBase() + nivel * 5;
        return new Personaje(nombre, nivel, clase, arma, armadura, escudo, vida, mana, clase.habilidad());
    }
}
