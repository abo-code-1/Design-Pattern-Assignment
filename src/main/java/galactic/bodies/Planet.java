package galactic.bodies;

public class Planet extends CelestialBody {

    private String atmosphereType;

    public Planet(String name, double x, double y, String atmosphereType) {
        super(name, x, y);
        this.atmosphereType = atmosphereType;
    }

    public String getAtmosphereType() {
        return atmosphereType;
    }
}
