package engine;

import living.entity.CombatEntity;

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
    public double getActionPoints(CombatEntity attacker) {
        return potion.getActionPoints();
    }

    public void usePotion(CombatEntity user) {
        switch (potion.getParameter()) {
            case "HP" -> user.heal(potion.getAmount());
            case "str" -> {}
            case "dex" -> {}
        }
    }
}
