package despues.model;

import despues.state.DroneState;

public class Drone {
    private DroneState state;

    public Drone(DroneState initialState) {
        this.state = initialState;
    }

    public void setState(DroneState state) {
        this.state = state;
        System.out.println(">> Drone state changed to: " + state.getClass().getSimpleName());
    }

    public DroneState getState() {
        return state;
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
