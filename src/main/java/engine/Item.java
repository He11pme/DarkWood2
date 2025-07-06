package engine;

public abstract class Item {

    private final String name;
    private final int cost;
    private final double actionPoints;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public double getActionPoints() {
        return actionPoints;
    }

    public Item(String name, int cost, double actionPoints) {
        this.name = name;
        this.cost = cost;
        this.actionPoints = actionPoints;
    }

    @Override
    public String toString() {
        return name + ". Стоимость: " + cost + " золотых.";
    }
}
