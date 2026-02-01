package galactic.cargo;

public class Cargo {

    private double weightKg;
    private String description;

    public Cargo(double weightKg, String description) {
        if (weightKg <= 0) {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }
        this.weightKg = weightKg;
        this.description = description;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public String getDescription() {
        return description;
    }
}
