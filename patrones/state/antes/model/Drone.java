package antes.model;

/**
 * Clase Drone SIN el patrón State aplicado.
 *
 * Problemas:
 * - Esta clase concentra toda la lógica y transiciones de estado mediante
 *   estructuras condicionales (switch / if-else).
 * - Viola el Principio de Abierto/Cerrado (OCP): Si se añade un nuevo estado
 *   (por ejemplo, LOW_BATTERY o RETURNING_HOME), hay que modificar esta clase
 *   y alterar todos sus métodos (fly, record, land).
 * - Viola el Principio de Responsabilidad Única (SRP): Drone es responsable
 *   tanto de su propio funcionamiento como de las reglas de comportamiento de cada estado.
 */
public class Drone {
    private DroneStatus state;

    public Drone(DroneStatus initialState) {
        this.state = initialState;
    }

    public void setState(DroneStatus state) {
        this.state = state;
        System.out.println(">> Drone state changed to: " + state.getStateName());
    }

    public DroneStatus getState() {
        return state;
    }

    public void fly() {
        switch (this.state) {
            case ON_GROUND -> System.out.println("Drone is taking off...");
            case FLYING -> System.out.println("Drone is already flying.");
            case EMERGENCY -> System.out.println("Emergency mode: cannot take off.");
        }
    }

    public void record() {
        switch (this.state) {
            case ON_GROUND -> System.out.println("Cannot record: drone is still on the ground.");
            case FLYING -> System.out.println("Recording from the air...");
            case EMERGENCY -> System.out.println("Emergency mode: recording canceled.");
        }
    }

    public void land() {
        switch (this.state) {
            case ON_GROUND -> System.out.println("Drone is already on the ground.");
            case FLYING -> System.out.println("Initiating landing sequence...");
            case EMERGENCY -> System.out.println("Forced landing initiated due to emergency.");
        }
    }
}
