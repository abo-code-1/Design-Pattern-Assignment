package galactic.drones;

public class HeavyDrone extends Drone {

    public HeavyDrone(String id, double maxPayloadKg) {
        super(id, maxPayloadKg);
    }

    @Override
    public double speedKmPerMin() {
        return 5.0;
    }
}
