package despues.state;

public class FlyingState implements DroneState {

    @Override
    public void fly() {
        System.out.println("Drone is already flying.");
    }

    @Override
    public void record() {
        System.out.println("Recording from the air...");
    }

    @Override
    public void land() {
        System.out.println("Initiating landing sequence...");
    }
}
