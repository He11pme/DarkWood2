package creatures;

import engine.Action;
import engine.Attack;
import engine.ListAttack;
import engine.ListWeapon;

import java.util.ArrayList;
import java.util.List;

public class Spider extends Enemy {

    private static final String NAME = "Spider";
    private static final int HEALTH = 60, DEX = 7, STR = 2;
    private static final int GOLD_REWARD = 5, EXP_REWARD = 200;

    public Spider() {
        super(NAME, HEALTH, DEX, STR, 1, ListWeapon.CLAWS, GOLD_REWARD, EXP_REWARD);

    }

}
