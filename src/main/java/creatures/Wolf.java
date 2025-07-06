package creatures;

import engine.Action;
import engine.Attack;
import engine.ListWeapon;

import java.util.List;

public class Wolf extends Enemy {

    private static final String NAME = "Wolf";
    private static final int HEALTH = 60, DEX = 4, STR = 5;
    private static final int GOLD_REWARD = 5, EXP_REWARD = 200;


    public Wolf() {
        super(NAME, HEALTH, DEX, STR,1, ListWeapon.FANGS, GOLD_REWARD, EXP_REWARD);
    }

}
