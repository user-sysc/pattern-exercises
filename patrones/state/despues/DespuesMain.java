package despues;

import despues.model.Drone;
import despues.state.EmergencyState;
import despues.state.FlyingState;
import despues.state.OnGroundState;

public class DespuesMain {
    public static void main(String[] args) {
        // 1. Iniciamos el dron en estado "En Tierra"
        Drone drone = new Drone(new OnGroundState());

        drone.record();     // Cannot record
        drone.fly();        // Taking off...

        // 2. Cambiamos el estado a "Volando"
        drone.setState(new FlyingState());
        drone.record();     // Recording
        drone.land();       // Landing

        // 3. Entramos en estado de "Emergencia"
        drone.setState(new EmergencyState());
        drone.fly();        // Emergency
        drone.record();     // Emergency
        drone.land();       // Forced landing
    }
}
