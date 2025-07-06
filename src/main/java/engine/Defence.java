package engine;

import creatures.Fighter;

public class Defence extends Action{

    private final String parameter; //block or dodge or escape
    private final int amount;

    public Defence(String name, String parameter, int amount) {
        super(name);
        this.parameter = parameter;
        this.amount = amount;
    }

    public String getParameter() {
        return parameter;
    }

    public void defence(Fighter fighter) {
        switch (parameter) {
            case "block" -> fighter.setImproveBlock(amount * fighter.getStr());
            case "dodge" -> fighter.setImproveDodge(amount * fighter.getDex());
        }
    }

    @Override
    public double getActionPoints(Fighter attacker) {
        return 1;
    }
}
