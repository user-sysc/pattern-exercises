# Abstract Factory - Antes y Despues

Ejercicio comparativo del patron de diseno **Abstract Factory** usando un
sistema que necesita trabajar con **dos familias de servicios** de IA: **Grok** y
**Gemini**.

Cada familia ofrece los mismos 3 servicios (consultoria, reportes y
notificaciones), y el objetivo es que el sistema pueda conmutar entre Grok y
Gemini **sin acoplarse** a ninguno de los dos.

El objetivo es ver el mismo problema resuelto de dos formas: primero con codigo
acoplado (`antes`) y luego aplicando el patron (`despues`).

## Estructura

```
abstract-factory/
├── antes/                       Sin Abstract Factory (acoplado)
│   ├── Main.java
│   ├── service/                 ConsultorService, ReporteService, NotificacionService
│   ├── grok/                    ConsultarGrok, ReporteGrok, NotificacionGrok
│   ├── gemini/                  ConsultarGemini, ReporteGemini, NotificacionGemini
│   └── client/Cliente.java      if/else + "new" de las clases concretas
│
├── despues/                     Con Abstract Factory
│   ├── Main.java
│   ├── service/                 Las mismas 3 interfaces (productos abstractos)
│   ├── grok/                    Las mismas 3 implementaciones de Grok
│   ├── gemini/                  Las mismas 3 implementaciones de Gemini
│   ├── factory/
│   │   ├── AbstractFactory.java (fabrica abstracta)
│   │   ├── FactoryGrok.java     (fabrica concreta)
│   │   └── FactoryGemini.java   (fabrica concreta)
│   └── client/Cliente.java      recibe la fabrica por constructor
│
└── README.md
```

> Nota: `antes/` y `despues/` usan los mismos paquetes (`service`, `grok`,
> `gemini`, `client`) y ambos tienen un `Main`. Por eso se compilan y ejecutan
> **por separado**, cada uno en su propia carpeta `out/`.

## Contratos (iguales en ambos lados)

| Interfaz | Metodo |
|---|---|
| `ConsultorService` | `String consultar()` |
| `ReporteService` | `String crearReporte()` |
| `NotificacionService` | `String notificar()` |

Hay **2 familias concretas** que implementan esos contratos: **Grok** y
**Gemini** (2 implementaciones por servicio).

## Antes: sin Abstract Factory

`Cliente` recibe un `String proveedor` y decide con un `if/else` que clases
instanciar. El cliente conoce y crea **todas** las clases concretas.

```java
public Cliente(String proveedor) {
    if (proveedor.equals("grok")) {
        consultor = new ConsultarGrok();
        reporte = new ReporteGrok();
        notificacion = new NotificacionGrok();
    } else if (proveedor.equals("gemini")) {
        consultor = new ConsultarGemini();
        reporte = new ReporteGemini();
        notificacion = new NotificacionGemini();
    } else {
        throw new IllegalArgumentException("Proveedor desconocido: " + proveedor);
    }
}
```

Problemas:

- **Acoplamiento fuerte**: el cliente depende de `grok.*` y `gemini.*`.
- **Viola Open/Closed (OCP)**: agregar un proveedor (p. ej. Claude) obliga a
  editar el `if/else`.
- **Riesgo de mezclar familias**: nada impide combinar un consultor de Grok con
  un reporte de Gemini.

## Despues: con Abstract Factory

- `ConsultorService`, `ReporteService` y `NotificacionService` son los
  **productos abstractos**.
- Las clases de `grok/` y `gemini/` son los **productos concretos**.
- `AbstractFactory` es la **fabrica abstracta**: declara
  `crearConsultor()`, `crearReporte()` y `crearNotificacion()`.
- `FactoryGrok` y `FactoryGemini` son las **fabricas concretas**: cada una crea
  la familia completa y consistente.
- `Cliente` recibe una `AbstractFactory` por constructor y solo habla contra las
  interfaces. Ya no menciona Grok ni Gemini.

```java
public Cliente(AbstractFactory factory) {
    this.consultor = factory.crearConsultor();
    this.reporte = factory.crearReporte();
    this.notificacion = factory.crearNotificacion();
}
```

El `Main` decide la familia una sola vez:

```java
Cliente clienteGrok   = new Cliente(new FactoryGrok());
Cliente clienteGemini = new Cliente(new FactoryGemini());
```

### Como se consulta sin escribir "grok" o "gemini"

El `Cliente` nunca pide el proveedor en cada llamada: la familia se fija al
inyectar la fabrica. Si quieres que ni el `Main` lo sepa, basta con mover la
eleccion a un punto de configuracion (variable de entorno, archivo de config o
un selector) y construir la fabrica alli:

```java
AbstractFactory factory = ProveedorConfig.actual(); // decide Grok o Gemini
Cliente cliente = new Cliente(factory);
```

## Como extender (agregar un proveedor nuevo, p. ej. Claude)

Con el patron:

1. `despues/claude/ConsultarClaude.java` implementa `ConsultorService` (igual
   para `ReporteClaude` y `NotificacionClaude`).
2. `despues/factory/FactoryClaude.java` implementa `AbstractFactory` y retorna
   los productos de Claude.
3. Listo: **no se toca `Cliente`**.

En la version `antes` habria que editar el `if/else` del `Cliente` y agregar un
caso nuevo con `new` de cada clase concreta.

## Compilar y ejecutar

Cada version se compila por separado (mismos paquetes y mismo `Main`):

```powershell
# ANTES
javac -encoding UTF-8 -d out/antes (Get-ChildItem antes -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out/antes Main

# DESPUES
javac -encoding UTF-8 -d out/despues (Get-ChildItem despues -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out/despues Main
```

Salida esperada (resumida):

```
=== ANTES: sin Abstract Factory ===
--- USANDO LA FAMILIA GROK ---
Consultando datos mediante el servicio de Grok...
Generando reporte analítico con Grok...
Enviando notificación a través de la red de Grok...
...
=== DESPUES: con Abstract Factory ===
--- USANDO LA FAMILIA GROK ---
Consultando datos mediante el servicio de Grok...
...
```
