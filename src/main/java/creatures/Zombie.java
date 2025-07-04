package creatures;

import engine.Action;
import engine.Attack;
import engine.ListWeapon;

import java.util.List;

public class Zombie extends Enemy {

    private static final String NAME = "Zombie";
    private static final int HEALTH = 100, DEX = 2, STR = 10;
    private static final int GOLD_REWARD = 5, EXP_REWARD = 5;

    public Zombie() {
        super(NAME, HEALTH, DEX, STR,2, ListWeapon.FISTS, GOLD_REWARD, EXP_REWARD);
    }

}
