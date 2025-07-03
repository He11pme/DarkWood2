package engine;

public class Weapon {

    private final String name;
    private final int power;
    private final int dex, str;
    private final int cost;
    private final double actionPoints;

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getDex() {
        return dex;
    }

    public int getStr() {
        return str;
    }

    public int getCost() {
        return cost;
    }

    public double getActionPoints() {
        return actionPoints;
    }

    public Weapon(String name, int power, int dex, int str, int cost, double actionPoints) {
        this.name = name;
        this.power = power;
        this.dex = dex;
        this.str = str;
        this.cost = cost;
        this.actionPoints = actionPoints;
    }

}
