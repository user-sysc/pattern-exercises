package despues.state;

public class EmergencyState implements DroneState {

    @Override
    public void fly() {
        System.out.println("Emergency mode: cannot take off.");
    }

    @Override
    public void record() {
        System.out.println("Emergency mode: recording canceled.");
    }

    @Override
    public void land() {
        System.out.println("Forced landing initiated due to emergency.");
    }
}
