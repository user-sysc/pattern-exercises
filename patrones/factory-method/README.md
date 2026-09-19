# Factory Method - Antes y Despues

Ejercicio comparativo del patron de diseno **Factory Method** usando un caso de
logistica: una empresa necesita entregar mercancia por **tierra (Camion)** o por
**mar (Barco)**.

El objetivo es ver el mismo problema resuelto de dos formas: primero con codigo
acoplado (`antes`) y luego aplicando el patron (`despues`).

## Estructura

```
factory-method/
├── antes/
│   ├── AntesMain.java
│   ├── domain/              Camion, Barco
│   ├── services/            LogisticaService (if/else + instanceof)
│   └── controller/          LogisticaController
├── despues/
│   ├── DespuesMain.java
│   ├── domain/
│   │   ├── Transporte.java  (Producto: interfaz)
│   │   ├── Camion.java      (Producto concreto)
│   │   ├── Barco.java       (Producto concreto)
│   │   └── factory/         (Creadores: Factory Method)
│   │       ├── TransporteFactory.java   (Creador abstracto)
│   │       ├── CamionFactory.java       (Creador concreto)
│   │       ├── BarcoFactory.java        (Creador concreto)
│   │       └── TransporteProvider.java  (Mapea tipo -> fabrica)
│   ├── services/            LogisticaService (usa el provider)
│   └── controller/          LogisticaController (delega en el service)
└── FactoryMethod-Good/      Ejemplo del profesor (Spring Boot) para comparar
```

Paquetes: `antes.*` y `despues.*`, para que `Camion`, `Barco` y
`LogisticaController` puedan repetirse en ambos lados sin chocar.

## Antes: sin Factory Method

`LogisticaService.planificarEntrega(String tipo)` decide con un `if/else` que
clase instanciar.

Como `Camion` y `Barco` **no comparten un tipo comun**, la unica variable capaz
de contener a cualquiera de los dos es `Object`. Eso hace perder el tipo real y
obliga a preguntar con `instanceof` y castear para llamar a `entregar()`.

Problemas:

- **Acoplamiento fuerte**: el service conoce `Camion` y `Barco`.
- **Viola Open/Closed (OCP)**: agregar un transporte obliga a editar el `if/else`.
- **Pierde polimorfismo**: al guardar en `Object` hay que castear a mano.

## Despues: con Factory Method

- `Transporte` es el **Producto**: interfaz que expone `entregar()`.
- `Camion` y `Barco` son los **Productos concretos**.
- `TransporteFactory` (en `domain/factory`) es el **Creador abstracto**:
  declara `createTransporte()` y expone `getTransporte()` como template.
- `CamionFactory` y `BarcoFactory` son los **Creadores concretos**: cada uno
  decide que producto crear.
- `TransporteProvider` es un **registro** `Map<String, TransporteFactory>` que
  mapea `"tierra" -> CamionFactory` y `"mar" -> BarcoFactory`, para que el
  service no tenga que decidir con `if/else`.
- `LogisticaService` le pide la fabrica al provider y ejecuta contra la
  abstraccion `Transporte`.
- `LogisticaController` delega en el service.

El `main` solo pide un tipo (`"tierra"`, `"mar"`). Ya no conoce ninguna clase
concreta: el provider resuelve la fabrica. Ese es el desacoplamiento que
buscamos.

## Como extender (agregar un transporte nuevo)

Con el patron, por ejemplo un tren:

1. `despues/domain/Tren.java` implementa `Transporte`.
2. `despues/domain/factory/TrenFactory.java` extiende `TransporteFactory` y
   retorna `new Tren()`.
3. Registrar la fabrica en `TransporteProvider`: `factories.put("tren", new TrenFactory())`.

En la version `antes` habria que tocar el `if/else` del service (y ademas el
`instanceof`); aca solo se agregan clases nuevas y una linea en el provider.
