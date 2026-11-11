# Patrón State - Antes y Después (Java)

Ejercicio comparativo del patrón de diseño **State** usando el caso de control de un **Dron**. Un dron responde de manera diferente a las mismas órdenes (`fly()`, `record()`, `land()`) dependiendo del estado en el que se encuentre (`OnGroundState`, `FlyingState`, `EmergencyState`).

El objetivo es ver el mismo problema resuelto de dos formas:
1. **Antes**: Sin el patrón State, usando un `enum` de estado y bloques condicionales (`switch`) dentro de la clase `Drone`.
2. **Después**: Aplicando el patrón State, encapsulando cada estado en una clase propia que implementa la interfaz `DroneState` y delegando el comportamiento desde `Drone` (Contexto).

---

## Estructura de Archivos

```text
state/
├── README.md
├── antes.drawio                 # Diagrama UML versión Antes (sin patrón)
├── despues.drawio               # Diagrama UML versión Después (con patrón State)
├── antes/
│   ├── AntesMain.java
│   └── model/
│       ├── Drone.java           # Clase monolítica con switch por estado
│       └── DroneStatus.java     # Enum con los posibles estados
└── despues/
    ├── DespuesMain.java
    ├── model/
    │   └── Drone.java           # Contexto que delega en DroneState
    └── state/
        ├── DroneState.java      # Interfaz común de estado
        ├── OnGroundState.java   # Estado: En tierra
        ├── FlyingState.java     # Estado: Volando
        └── EmergencyState.java  # Estado: Emergencia
```

---

## Antes: Sin patrón State

En `antes/model/Drone.java`, la clase `Drone` gestiona directamente el comportamiento para cada estado mediante estructuras `switch`:

```java
public void fly() {
    switch (this.state) {
        case ON_GROUND -> System.out.println("Drone is taking off...");
        case FLYING -> System.out.println("Drone is already flying.");
        case EMERGENCY -> System.out.println("Emergency mode: cannot take off.");
    }
}
```

### Problemas del enfoque "Antes":
- **Violación del Principio Abierto/Cerrado (OCP)**: Si se agrega un nuevo estado (ej. `LowBatteryState` o `ReturningHomeState`), se debe modificar la clase `Drone` y alterar **todos** los métodos (`fly`, `record`, `land`).
- **Violación del Principio de Responsabilidad Única (SRP)**: La clase `Drone` asume dos responsabilidades: gestionar la entidad del dron y definir las reglas y restricciones específicas de cada estado.
- **Explosión de condicionales**: Con más estados o más acciones, los bloques condicionales crecen rápidamente y se vuelven difíciles de mantener y probar.

---

## Después: Con el patrón State

En `despues/`, separamos las responsabilidades usando el patrón **State**:

- **Interfaz de Estado (`DroneState`)**: Declara los métodos que deben responder los estados (`fly()`, `record()`, `land()`).
- **Estados Concretos (`OnGroundState`, `FlyingState`, `EmergencyState`)**: Cada clase encapsula la lógica y las restricciones de su propio estado.
- **Contexto (`Drone`)**: Mantiene una referencia al estado actual (`DroneState`) y delega todas las acciones en él:

```java
public class Drone {
    private DroneState state;

    public Drone(DroneState initialState) {
        this.state = initialState;
    }

    public void setState(DroneState state) {
        this.state = state;
        System.out.println(">> Drone state changed to: " + state.getClass().getSimpleName());
    }

    public void fly() {
        this.state.fly();
    }

    public void record() {
        this.state.record();
    }

    public void land() {
        this.state.land();
    }
}
```

### Ventajas del enfoque "Después":
- **Extensibilidad limpia (OCP)**: Para añadir un nuevo estado, solo se crea una nueva clase que implemente `DroneState`, sin tocar la clase `Drone` ni los estados existentes.
- **Alta cohesión (SRP)**: La lógica de cada estado está aislada en su propia clase.
- **Eliminación de estructuras condicionales**: El polimorfismo se encarga de despachar la acción correspondiente al estado actual.

---

## Compilación y Ejecución

Desde la carpeta `state`:

### Compilar y ejecutar la versión ANTES:

```powershell
javac -encoding UTF-8 -d out/antes (Get-ChildItem antes -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out/antes antes.AntesMain
```

### Compilar y ejecutar la versión DESPUÉS:

```powershell
javac -encoding UTF-8 -d out/despues (Get-ChildItem despues -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out/despues despues.DespuesMain
```

### Salida esperada (idéntica en ambas versiones):

```text
Cannot record: drone is still on the ground.
Drone is taking off...
>> Drone state changed to: FlyingState
Recording from the air...
Initiating landing sequence...
>> Drone state changed to: EmergencyState
Emergency mode: cannot take off.
Emergency mode: recording canceled.
Forced landing initiated due to emergency.
```
