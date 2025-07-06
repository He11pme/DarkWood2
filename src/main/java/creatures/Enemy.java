package creatures;

import engine.ListAttack;
import engine.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends Fighter {

    private final int goldReward;
    private final int expReward;
    private boolean isLooted = false;

    public int getGoldReward() {
        return goldReward;
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

    public Enemy(String name, int maxHealth, int dex, int str, int maxActionPoints, Weapon weapon, int goldReward, int expReward) {
        super(name, maxHealth, dex, str, maxActionPoints, weapon);
        this.goldReward = goldReward;
        this.expReward = expReward;
        actionInFight = new ArrayList<>(List.of(ListAttack.BASIC));
    }

    @Override
    public String toString() {
        return getName() + ": HP = " + getHealth() + "/" + getMaxHealth();
    }
}
