package living.entity;

import engine.Action;
import engine.ListAttack;
import engine.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends CombatEntity {

    private final int lootGoldAmount;
    private final int expReward;
    private boolean isLooted = false;

    public Enemy(
            String name,
            int agility,
            int strength,
            int maxActionPoints,
            int maxHealth,
            Weapon equippedWeapon,
            int lootGoldAmount,
            int expReward)
    {
        super(name, agility, strength, maxActionPoints, maxHealth, equippedWeapon);
        this.lootGoldAmount = lootGoldAmount;
        this.expReward = expReward;

        setAllCombatActions(new ArrayList<>(List.of(ListAttack.BASIC)));
    }

    public int getLootGoldAmount() {
        return lootGoldAmount;
    }

    public int getExpReward() {
        return expReward;
    }

    public boolean isLooted() {
        return isLooted;
    }

    public void setLooted(boolean looted) {
        isLooted = looted;
    }

    @Override
    public List<Action> getAvailableCombatActions() {
        return getAllCombatActions();
    }

    @Override
    public String toString() {
        return getName() + ": HP = " + getCurrentHealth() + "/" + getMaxHealth();
    }
}
