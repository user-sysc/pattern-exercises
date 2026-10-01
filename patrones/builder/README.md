# Builder - Antes y Despues

Ejercicio comparativo del patron de diseno **Builder** usando un caso de
ensamblaje de **computadores**: un mismo proceso de construccion debe producir
computadores distintos (gamer, oficina) sin recurrir a constructores gigantes.

El objetivo es ver el mismo problema resuelto de dos formas: primero con codigo
acoplado (`antes`) y luego aplicando el patron (`despues`).

## Estructura

```
builder/
├── antes/
│   ├── AntesMain.java
│   ├── domain/                  Computador (constructores telescopicos)
│   └── controller/              ComputadorController (llama al ctor gigante)
├── despues/
│   ├── DespuesMain.java
│   ├── domain/
│   │   ├── Computador.java              (Producto, inmutable)
│   │   └── builder/
│   │       ├── ComputadorBuilder.java          (Builder: pasos)
│   │       ├── ComputadorGamerBuilder.java     (Builder concreto)
│   │       ├── ComputadorOficinaBuilder.java   (Builder concreto)
│   │       └── EnsambladorDirector.java        (Director: orden de los pasos)
│   └── controller/              ComputadorController (usa builder + director)
└── README.md
```

Paquetes: `antes.*` y `despues.*`, para que `Computador` y
`ComputadorController` puedan repetirse en ambos lados sin chocar.

## Antes: sin Builder

`antes/domain/Computador.java` usa **constructores telescopicos**: un
constructor por cada cantidad de parametros, hasta llegar a uno de 9:

```java
Computador pc = new Computador(
        "Intel Core i9", 32, "RTX 4070", "1TB SSD NVMe", "750W 80+ Gold",
        true, true, true, "Windows 11");
```

Problemas:

- **Ilegible**: los parametros son posicionales; no se sabe que es cada uno.
- **Booleanos sin nombre**: no se sabe que significa cada `true/false`.
- **Facil equivocarse**: invertir dos argumentos del mismo tipo compila igual.
- **Rigido**: cada combinacion nueva obliga a crear otro constructor.
- **Viola el principio de responsabilidad**: el cliente conoce el orden interno
  de las partes del objeto.

## Despues: con Builder + Director

- `Computador` es el **Producto**: inmutable, con getters y `toString()`.
- `ComputadorBuilder` es el **Builder**: declara los pasos
  (`construirCpu()`, `construirRam()`, ..., `getResultado()`).
- `ComputadorGamerBuilder` y `ComputadorOficinaBuilder` son los **Builders
  concretos**: cada uno decide los valores de su representacion.
- `EnsambladorDirector` conoce el **orden** de los pasos y lo aplica siempre
  igual; el cliente no repite esa secuencia.
- `ComputadorController` solo elige el builder concreto y pide al director que
  ensamble.

```java
EnsambladorDirector director = new EnsambladorDirector();
Computador gamer   = director.ensamblar(new ComputadorGamerBuilder());
Computador oficina = director.ensamblar(new ComputadorOficinaBuilder());
```

El mismo algoritmo de construccion produce representaciones distintas: eso es
la idea central de Builder, *separar la construccion de un objeto complejo de
su representacion final*.

## Como extender (agregar una representacion nueva, p. ej. servidor)

Con el patron:

1. `despues/domain/builder/ComputadorServidorBuilder.java` implementa
   `ComputadorBuilder` con los valores del servidor.
2. Listo: **no se toca el Director ni el Producto**; se reutiliza el mismo
   proceso de construccion.

En la version `antes` habria que agregar otro constructor (o mas parametros) y
tocar todas las llamadas existentes.

## Compilar y ejecutar

Cada version se compila por separado (mismos nombres de clase en ambos lados):

```powershell
# ANTES
javac -encoding UTF-8 -d out/antes (Get-ChildItem antes -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out/antes antes.AntesMain

# DESPUES
javac -encoding UTF-8 -d out/despues (Get-ChildItem despues -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out/despues despues.DespuesMain
```

Salida esperada (resumida):

```
=== ANTES: Computador sin Builder ===
--- PC Gamer ---
CPU: Intel Core i9
RAM: 32 GB
...

=== DESPUES: Computador con Builder ===
--- PC Gamer ---
CPU: Intel Core i9
RAM: 32 GB
...
```
