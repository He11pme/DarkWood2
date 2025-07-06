package living.entity;

import engine.ListWeapon;

public class Spider extends Enemy {

    private static final String NAME = "Spider";
    private static final int MAX_HEALTH = 60, AGILITY = 7, STRENGTH = 2;
    private static final int LOOT_GOLD_AMOUNT = 5, EXP_REWARD = 15;

    public Spider() {
        super(NAME, AGILITY, STRENGTH, 1, MAX_HEALTH, ListWeapon.CLAWS, LOOT_GOLD_AMOUNT, EXP_REWARD);
    }

}
