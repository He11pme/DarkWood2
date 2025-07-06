package engine;

import creatures.Fighter;

public class Potion extends Item {

    private final String effect;    //buff or debuff
    private final String parameter;
    private final int amount;

    public String getEffect() {
        return effect;
    }

    public String getParameter() {
        return parameter;
    }

    public int getAmount() {
        return amount;
    }

    public Potion(String name, int cost, double actionPoints, String effect, String parameter, int amount) {
        super(name, cost, actionPoints);
        this.effect = effect;
        this.parameter = parameter;
        this.amount = amount;
    }

}

