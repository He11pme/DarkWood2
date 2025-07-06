package living.entity;

import engine.ListWeapon;

public class Zombie extends Enemy {

    private static final String NAME = "Zombie";
    private static final int MAX_HEALTH = 100, AGILITY = 2, STRENGTH = 7;
    private static final int LOOT_GOLD_AMOUNT = 5, EXP_REWARD = 15;

    public Zombie() {
        super(NAME, AGILITY, STRENGTH, 1, MAX_HEALTH, ListWeapon.CLAWS, LOOT_GOLD_AMOUNT, EXP_REWARD);
    }

}
