package despues.state;

public class OnGroundState implements DroneState {

    @Override
    public void fly() {
        System.out.println("Drone is taking off...");
    }

    @Override
    public void record() {
        System.out.println("Cannot record: drone is still on the ground.");
    }

    @Override
    public void land() {
        System.out.println("Drone is already on the ground.");
    }
}
