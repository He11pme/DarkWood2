package creatures;

import engine.Action;
import engine.Attack;
import engine.ListAttack;
import engine.ListWeapon;
import text_styler.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public class Player extends Fighter {

    private int gold = 10;
    private int exp = 0;


    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

    public Player(String name, int maxHealth, int dex, int str) {
        super(name, maxHealth, dex, str, 2, ListWeapon.FISTS);
        actionInFight = new ArrayList<>(List.of(ListAttack.BASIC, ListAttack.WHIRL, ListAttack.REND));
    }

    private boolean checkUpLevel() {
        if (exp / 100 > level - 1) {
            System.out.println(TextFormatter.of("Отдохните в деревне, чтобы повысить уровень.")
                    .colorize(TextFormatter.GREEN));
            return true;
        }
        return false;
    }

    public void levelUp(int health, int str, int dex) {
        level += 1;
        this.maxHealth += (health + 2);
        this.str += str;
        this.dex += dex;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public boolean spendGold(int amount) {
        if (gold < amount) return false;
        else {
            gold -= amount;
            return true;
        }
    }

    public void addExp(int amount) {
        exp += amount;
        checkUpLevel();
    }

    @Override
    public List<Action> getActionInFight() {
        List<Action> availableActions = new ArrayList<>();
        for (Action action : actionInFight) {
            if (action instanceof Attack attack) {
                if (getStr() >= attack.getMinStr() && getDex() >= attack.getMinDex()) {
                    availableActions.add(attack);
                }
            }
        }
        return availableActions;
    }

    @Override
    public String toString() {
        return getName() + ": HP = " + getHealth() + "/" + getMaxHealth() + "; Action points = " + getActionPoints() + "; Str = " + getStr() + "; Dex = " + getDex() + "; Weapon: " + getWeapon().getName();
    }

}
