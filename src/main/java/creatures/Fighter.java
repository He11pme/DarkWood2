package creatures;

import engine.Action;
import engine.Weapon;

import java.util.List;

public abstract class Fighter extends Creature{

    int maxHealth;
    private int health;
    int dex;
    int str;
    int maxActionPoints;
    double actionPoints;
    int level = 1;
    private boolean live = true;
    private Weapon weapon;

    List<Action> actionInFight;

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getDex() {
        return dex;
    }

    public int getStr() {
        return str;
    }

    public int getMaxActionPoints() {
        return maxActionPoints + dex / 4;
    }

    public double getActionPoints() {
        return actionPoints;
    }

    public int getLevel() {
        return level;
    }

    public boolean isLive() {
        return live;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public List<Action> getActionInFight() {
        return actionInFight;
    }

    public Fighter(String name, int maxHealth, int dex, int str, int maxActionPoints, Weapon weapon) {
        super(name);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.dex = dex;
        this.str = str;
        this.maxActionPoints = maxActionPoints;
        this.actionPoints = maxActionPoints;
        this.weapon = weapon;
    }

    public void takeDamage(int damage) {
        if (health > damage) health -= damage;
        else {
            live = false;
            health = 0;
        }
    }

    public void spendActionPoints(double amount) {
        actionPoints -= amount;
    }

    public void resetActionPoints() {
        actionPoints = getMaxActionPoints();
    }
}
