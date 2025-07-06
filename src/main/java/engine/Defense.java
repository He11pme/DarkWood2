package engine;

import living.entity.CombatEntity;

public class Defense extends Action{

    private final String parameter; //block or dodge or escape
    private final int amount;

    public Defense(String name, String parameter, int amount) {
        super(name);
        this.parameter = parameter;
        this.amount = amount;
    }

    public String getParameter() {
        return parameter;
    }

    public void defense(CombatEntity combatEntity) {
        switch (parameter) {
            case "block" -> combatEntity.setBlockDamageReduction(amount * combatEntity.getStrength());
            case "dodge" -> combatEntity.setDodgeChanceBonus(amount * combatEntity.getAgility());
        }
    }

    @Override
    public double getActionPoints(CombatEntity attacker) {
        return 1;
    }
}
