package engine;

import creatures.Fighter;

public class UsePotion extends Action{

    private final Potion potion;

    public Potion getPotion() {
        return potion;
    }

    public UsePotion(String name, Potion potion) {
        super(name + potion.getName());
        this.potion = potion;
    }

    @Override
    public double getActionPoints(Fighter attacker) {
        return potion.getActionPoints();
    }

    public void usePotion(Fighter user) {
        switch (potion.getParameter()) {
            case "HP" -> user.treatment(potion.getAmount());
            case "str" -> {}
            case "dex" -> {}
        }
    }
}
