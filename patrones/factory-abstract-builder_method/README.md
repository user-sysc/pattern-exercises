# Factory Method + Abstract Factory + Builder - Creacion de personajes

Ejercicio que muestra como **tres patrones creacionales trabajan juntos** en un
mismo sistema, cada uno en una etapa distinta de la creacion de un objeto:

| Patron | Pregunta que responde | Producto que crea |
|---|---|---|
| **Factory Method** | ¿**que tipo** de personaje? | `ClasePersonaje` (Guerrero / Mago / Arquero) |
| **Abstract Factory** | ¿**que familia** de equipo? | `Arma` + `Armadura` + `Escudo` (Medieval o Futurista) |
| **Builder** | ¿**como se ensambla** el personaje final? | `Personaje` (nombre, nivel, clase, equipo, vida, mana) |

La clave es que **no se pisan**: Factory Method decide el TIPO, Abstract Factory
decide la FAMILIA de objetos relacionados y Builder ENSAMBLA el objeto complejo
paso a paso.

```
tipo  ──► Factory Method   ──► ClasePersonaje (Guerrero/Mago/Arquero)
mundo ──► Abstract Factory ──► Equipo (Arma + Armadura + Escudo de una familia)
                     │
                     ▼
                  Builder ──► PERSONAJE FINAL
```

## Estructura

```
factory-abstract-builder_method/
├── Main.java                              (demo; sin package)
├── domain/
│   ├── Personaje.java                     Producto final del Builder
│   ├── clase/                             FACTORY METHOD
│   │   ├── ClasePersonaje.java            (Producto: interfaz)
│   │   ├── Guerrero.java / Mago.java / Arquero.java      (Productos concretos)
│   │   ├── CreadorClasePersonaje.java     (Creador abstracto)
│   │   ├── CreadorGuerrero.java / CreadorMago.java / CreadorArquero.java
│   │   └── ClasePersonajeProvider.java    (Map< String, CreadorClasePersonaje >)
│   ├── equipo/                            ABSTRACT FACTORY
│   │   ├── Arma.java / Armadura.java / Escudo.java       (Productos abstractos)
│   │   ├── Espada.java / ArmaduraPlacas.java / EscudoMadera.java
│   │   ├── PistolaLaser.java / ArmaduraTecnologica.java / EscudoEnergetico.java
│   │   ├── FabricaEquipamiento.java       (Fabrica abstracta)
│   │   ├── FabricaMedieval.java / FabricaFuturista.java  (Fabricas concretas)
│   │   └── FabricaEquipamientoProvider.java (Map< String, FabricaEquipamiento >)
│   └── builder/                           BUILDER
│       ├── PersonajeBuilder.java          (construccion paso a paso)
│       └── DirectorPersonaje.java         (secuencia estandar de armado)
├── service/
│   └── CreacionPersonajeService.java      (une los tres patrones)
└── controller/
    └── PersonajeController.java
```

## Factory Method: ¿que tipo de personaje?

- `ClasePersonaje` es el **Producto**: `nombre()`, `vidaBase()`, `manaBase()`,
  `habilidad()`.
- `Guerrero`, `Mago` y `Arquero` son los **Productos concretos**.
- `CreadorClasePersonaje` es el **Creador abstracto**: expone `getClase()` como
  template y declara `crearClase()`.
- `CreadorGuerrero`, `CreadorMago` y `CreadorArquero` son los **Creadores
  concretos**.
- `ClasePersonajeProvider` mapea `"guerrero"`, `"mago"`, `"arquero"` con su
  creador, para que el service no decida con `if/else`.

## Abstract Factory: ¿que familia de equipo?

- `Arma`, `Armadura` y `Escudo` son los **Productos abstractos**.
- La familia **Medieval** (`Espada`, `ArmaduraPlacas`, `EscudoMadera`) y la
  familia **Futurista** (`PistolaLaser`, `ArmaduraTecnologica`,
  `EscudoEnergetico`) son los **Productos concretos**.
- `FabricaEquipamiento` es la **Fabrica abstracta**: `crearArma()`,
  `crearArmadura()`, `crearEscudo()`.
- `FabricaMedieval` y `FabricaFuturista` son las **Fabricas concretas**: cada
  una entrega una familia completa y consistente (no se mezclan mundos).
- `FabricaEquipamientoProvider` mapea `"medieval"` y `"futurista"` con su
  fabrica.

## Builder: ¿como se construye el personaje?

`PersonajeBuilder` expone metodos fluidos y construye el objeto complejo:

```java
Personaje p = new PersonajeBuilder()
        .nombre("Arthas")
        .nivel(10)
        .clase(clase)              // Factory Method
        .arma(fabrica.crearArma()) // Abstract Factory
        .armadura(fabrica.crearArmadura())
        .escudo(fabrica.crearEscudo())
        .build();                  // Builder calcula vida/mana y crea el Personaje
```

`DirectorPersonaje` conoce la **secuencia estandar** de armado y recibe la clase
y la familia ya creadas por los otros dos patrones.

## Como trabajan juntos

`CreacionPersonajeService` es el unico punto que conoce a los tres patrones:

```java
ClasePersonaje clase = claseProvider.get(tipo).getClase();   // Factory Method
FabricaEquipamiento fabrica = equipoProvider.get(mundo);     // Abstract Factory
return director.ensamblar(new PersonajeBuilder(), nombre, nivel, clase, fabrica); // Builder
```

## Como extender

- **Nuevo tipo de personaje** (p. ej. Paladin): crear `Paladin implements
  ClasePersonaje`, `CreadorPaladin extends CreadorClasePersonaje` y registrarlo
  en `ClasePersonajeProvider`.
- **Nuevo mundo** (p. ej. Espacial): crear los productos de esa familia,
  `FabricaEspacial implements FabricaEquipamiento` y registrarla en
  `FabricaEquipamientoProvider`.
- **Nuevos atributos del personaje**: agregar un paso al `PersonajeBuilder`
  (p. ej. `.mascota(...)`) sin tocar constructores ni llamadas existentes.

En ningun caso hay que modificar el `service` ni el `controller`.

## Compilar y ejecutar

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem . -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out Main
```

Salida esperada (resumida):

```
=== Sistema de creacion de personajes ===
(Factory Method + Abstract Factory + Builder)

--- Arthas | tipo=guerrero | mundo=medieval ---
Nombre: Arthas
Clase: Guerrero
Nivel: 10
Vida: 220
Mana: 70
Habilidad: Golpe critico
Arma: Espada medieval (15 danio)
...
```
