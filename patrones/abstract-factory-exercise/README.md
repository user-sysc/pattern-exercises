# Abstract Factory - Antes y Despues (Muebles)

Ejercicio comparativo del patron de diseno **Abstract Factory** usando el
ejemplo clasico de una tienda de muebles.

Existe una **familia de productos** relacionados: `Silla` + `Sofa` + `Mesilla`.
Esa familia viene en **3 variantes**: `Moderna`, `Victoriana` y `ArtDeco`. El
objetivo es crear muebles que **combinen entre si** (todos de la misma variante)
sin que el codigo cliente dependa de las clases concretas.

El ejercicio resuelve el mismo problema de dos formas: primero con codigo
acoplado (`antes`) y luego aplicando el patron (`despues`).

## Estructura

```
abstract-factory-exercise/
├── antes/                       Sin Abstract Factory (acoplado)
│   ├── Main.java
│   ├── muebles/                 Silla, Sofa, Mesilla (productos abstractos)
│   ├── moderna/                 SillaModerna, SofaModerno, MesillaModerna
│   ├── victoriana/              SillaVictoriana, SofaVictoriano, MesillaVictoriana
│   ├── artdeco/                 SillaArtDeco, SofaArtDeco, MesillaArtDeco
│   └── client/Cliente.java      if/else + "new" de las clases concretas
│
├── despues/                     Con Abstract Factory
│   ├── Main.java
│   ├── muebles/                 Las mismas 3 interfaces (productos abstractos)
│   ├── moderna/                 Las mismas 3 implementaciones de Moderna
│   ├── victoriana/              Las mismas 3 implementaciones de Victoriana
│   ├── artdeco/                 Las mismas 3 implementaciones de ArtDeco
│   ├── factory/
│   │   ├── MuebleFactory.java   (fabrica abstracta)
│   │   ├── FactoryModerna.java  (fabrica concreta)
│   │   ├── FactoryVictoriana.java
│   │   └── FactoryArtDeco.java
│   └── client/Cliente.java      recibe la fabrica por constructor
│
└── README.md
```

> Nota: `antes/` y `despues/` usan los mismos paquetes (`muebles`, `moderna`,
> `victoriana`, `artdeco`, `client`) y ambos tienen un `Main`. Por eso se
> compilan y ejecutan **por separado**, cada uno en su propia carpeta `out/`.

## Contratos (iguales en ambos lados)

| Interfaz  | Metodo                |
| --------- | --------------------- |
| `Silla`   | `String sentarse()`   |
| `Sofa`    | `String recostarse()` |
| `Mesilla` | `String colocar()`    |

Hay **3 familias concretas** que implementan esos contratos: **Moderna**,
**Victoriana** y **ArtDeco** (3 implementaciones por producto = 9 clases).

## Antes: sin Abstract Factory

`Cliente` recibe un `String estilo` y decide con un `if/else` que clases
instanciar. El cliente conoce y crea **todas** las clases concretas.

```java
public Cliente(String estilo) {
    if (estilo.equals("moderna")) {
        silla = new SillaModerna();
        sofa = new SofaModerno();
        mesilla = new MesillaModerna();
    } else if (estilo.equals("victoriana")) {
        silla = new SillaVictoriana();
        sofa = new SofaVictoriano();
        mesilla = new MesillaVictoriana();
    } else if (estilo.equals("artdeco")) {
        silla = new SillaArtDeco();
        sofa = new SofaArtDeco();
        mesilla = new MesillaArtDeco();
    } else {
        throw new IllegalArgumentException("Estilo desconocido: " + estilo);
    }
}
```

Problemas:

- **Acoplamiento fuerte**: el cliente depende de `moderna.*`, `victoriana.*` y
  `artdeco.*`.
- **Viola Open/Closed (OCP)**: agregar una variante (p. ej. Rustica) obliga a
  editar el `if/else`.
- **Riesgo de mezclar familias**: nada impide combinar una silla moderna con un
  sofa victoriano.

## Despues: con Abstract Factory

- `Silla`, `Sofa` y `Mesilla` son los **productos abstractos**.
- Las clases de `moderna/`, `victoriana/` y `artdeco/` son los **productos
  concretos**.
- `MuebleFactory` es la **fabrica abstracta**: declara `crearSilla()`,
  `crearSofa()` y `crearMesilla()`.
- `FactoryModerna`, `FactoryVictoriana` y `FactoryArtDeco` son las **fabricas
  concretas**: cada una crea la familia completa y consistente.
- `Cliente` recibe una `MuebleFactory` por constructor y solo habla contra las
  interfaces. Ya no menciona Moderna, Victoriana ni ArtDeco.

```java
public Cliente(MuebleFactory factory) {
    this.silla = factory.crearSilla();
    this.sofa = factory.crearSofa();
    this.mesilla = factory.crearMesilla();
}
```

El `Main` decide la familia una sola vez:

```java
Cliente clienteModerno   = new Cliente(new FactoryModerna());
Cliente clienteVictoriano = new Cliente(new FactoryVictoriana());
Cliente clienteArtDeco   = new Cliente(new FactoryArtDeco());
```

### Como se elige la familia sin escribirla en el cliente

El `Cliente` nunca pide el estilo en cada llamada: la familia se fija al
inyectar la fabrica. Si quieres que ni el `Main` lo sepa, basta con mover la
eleccion a un punto de configuracion (variable de entorno, archivo de config o
un selector) y construir la fabrica alli:

```java
MuebleFactory factory = EstiloConfig.actual(); // decide Moderna, Victoriana o ArtDeco
Cliente cliente = new Cliente(factory);
```

## Como extender (agregar una variante nueva, p. ej. Rustica)

Con el patron:

1. `despues/rustica/SillaRustica.java` implementa `Silla` (igual para
   `SofaRustico` y `MesillaRustica`).
2. `despues/factory/FactoryRustica.java` implementa `MuebleFactory` y retorna
   los productos de Rustica.
3. Listo: **no se toca `Cliente`**.

En la version `antes` habria que editar el `if/else` del `Cliente` y agregar un
caso nuevo con `new` de cada clase concreta.
