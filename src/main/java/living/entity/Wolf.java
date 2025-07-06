package living.entity;

import engine.ListWeapon;

public class Wolf extends Enemy {

    private static final String NAME = "Wolf";
    private static final int MAX_HEALTH = 60, AGILITY = 4, STRENGTH = 5;
    private static final int LOOT_GOLD_AMOUNT = 5, EXP_REWARD = 15;

    public Wolf() {
        super(NAME, AGILITY, STRENGTH, 1, MAX_HEALTH, ListWeapon.CLAWS, LOOT_GOLD_AMOUNT, EXP_REWARD);
    }

}
