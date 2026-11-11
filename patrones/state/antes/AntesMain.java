package antes;

import antes.model.Drone;
import antes.model.DroneStatus;

public class AntesMain {
    public static void main(String[] args) {
        // 1. Iniciamos el dron en estado "En Tierra"
        Drone drone = new Drone(DroneStatus.ON_GROUND);

        drone.record();     // Cannot record
        drone.fly();        // Taking off...

        // 2. Cambiamos el estado a "Volando"
        drone.setState(DroneStatus.FLYING);
        drone.record();     // Recording
        drone.land();       // Landing

        // 3. Entramos en estado de "Emergencia"
        drone.setState(DroneStatus.EMERGENCY);
        drone.fly();        // Emergency
        drone.record();     // Emergency
        drone.land();       // Forced landing
    }
}
