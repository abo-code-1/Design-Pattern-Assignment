package galactic.drones;

public class LightDrone extends Drone {

    public LightDrone(String id, double maxPayloadKg) {
        super(id, maxPayloadKg);
    }

    @Override
    public double speedKmPerMin() {
        return 10.0;
    }
}
