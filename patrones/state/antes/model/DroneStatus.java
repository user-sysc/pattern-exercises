package antes.model;

public enum DroneStatus {
    ON_GROUND("OnGroundState"),
    FLYING("FlyingState"),
    EMERGENCY("EmergencyState");

    private final String stateName;

    DroneStatus(String stateName) {
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public String toString() {
        return stateName;
    }
}
