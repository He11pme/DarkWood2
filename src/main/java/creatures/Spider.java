package creatures;

import engine.Action;
import engine.Attack;
import engine.ListAttack;
import engine.ListWeapon;

import java.util.ArrayList;
import java.util.List;

public class Spider extends Enemy {

    private static final String NAME = "Spider";
    private static final int HEALTH = 100, DEX = 2, STR = 10;
    private static final int GOLD_REWARD = 5, EXP_REWARD = 5;

    public Spider() {
        super(NAME, HEALTH, DEX, STR, 2, ListWeapon.CLAWS, GOLD_REWARD, EXP_REWARD);

    }

}
