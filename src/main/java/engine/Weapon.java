package engine;

public class Weapon extends Item {

    private final int power;
    private final int dex, str;

    public int getPower() {
        return power;
    }

    public int getDex() {
        return dex;
    }

    public int getStr() {
        return str;
    }

    public Weapon(String name, int cost, double actionPoints, int power, int dex, int str) {
        super(name, cost, actionPoints);
        this.power = power;
        this.dex = dex;
        this.str = str;
    }

}
