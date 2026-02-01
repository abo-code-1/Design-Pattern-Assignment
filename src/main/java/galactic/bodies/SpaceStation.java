package galactic.bodies;

public class SpaceStation extends CelestialBody {

    private int level;

    public SpaceStation(String name, double x, double y, int level) {
        super(name, x, y);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
