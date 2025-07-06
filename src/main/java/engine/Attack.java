package engine;

import living.entity.Enemy;
import living.entity.CombatEntity;
import text_styler.TextFormatter;

public class Attack extends Action {

    private final double attackFactor;
    private final double actionPointsFactor;
    private final double baseCritChance;
    private final double baseMissChance;
    private final int minStr;
    private final int minDex;

    private final static double CRIT_FACTOR = 1.5;
    private final static double DEX_FACTOR_CRIT = 3;
    private final static double DEX_FACTOR_MISS = 1.0;
    private final static double STR_FACTOR = 6;
    private final static double LEVEL_FACTOR_CRIT = 1;
    private final static double LEVEL_FACTOR_DAMAGE = 1;
    private final static double LEVEL_FACTOR_MISS = 0.1;
    private final static int MAX_CRIT_CHANCE = 50;
    private final static int MAX_MISS_CHANCE = 50;

    public int getMinDex() {
        return minDex;
    }

    public int getMinStr() {
        return minStr;
    }

    public Attack(
            String name,
            double attackFactor,
            double actionPointsFactor,
            double baseCritChance,
            double baseMissChance,
            int minStr,
            int minDex
    ) {
        super(name);
        this.attackFactor = attackFactor;
        this.actionPointsFactor = actionPointsFactor;
        this.baseCritChance = baseCritChance;
        this.baseMissChance = baseMissChance;
        this.minStr = minStr;
        this.minDex = minDex;
    }

    public void attack(CombatEntity attacker, CombatEntity opponent) {
        boolean crit = getCrit(attacker, opponent);
        boolean miss = getMiss(attacker, opponent);
        int blockDamage = opponent.getBlockDamageReduction();
        int damage = miss ? 0 : getDamage(crit, attacker, opponent);
        DataAttack dataAttack = new DataAttack(crit, miss, damage, blockDamage, attacker);
        opponent.takeDamage(damage - blockDamage);
        System.out.println(dataAttack);
    }

    private boolean getCrit(CombatEntity attacker, CombatEntity opponent) {

        double critChance =
                baseCritChance
                        + (attacker.getAgility() - opponent.getAgility()) * DEX_FACTOR_CRIT
                        + attacker.getLevel() * LEVEL_FACTOR_CRIT;

        if (critChance > MAX_CRIT_CHANCE) critChance = MAX_CRIT_CHANCE;
        return Math.random() * 100 < critChance;

    }

    private int getDamage(boolean crit, CombatEntity attacker, CombatEntity opponent) {
        int damage = (int) (attacker.getEquippedWeapon().getPower() * attackFactor
                + attacker.getStrength() * STR_FACTOR
                + (1 + (attacker.getLevel() - opponent.getLevel()) * LEVEL_FACTOR_DAMAGE));

        return crit ? (int) (damage * CRIT_FACTOR) : damage;
    }

    private boolean getMiss(CombatEntity attacker, CombatEntity opponent) {

        double missChance = baseMissChance
                + (opponent.getAgility() - attacker.getAgility()) * DEX_FACTOR_MISS
                - attacker.getLevel() * LEVEL_FACTOR_MISS
                + opponent.getDodgeChanceBonus();

        if (missChance > MAX_MISS_CHANCE) missChance = MAX_MISS_CHANCE;
        return Math.random() * 100 < missChance;

    }

    @Override
    public double getActionPoints(CombatEntity attacker) {
        return attacker.getEquippedWeapon().getActionPoints() * actionPointsFactor;
    }

    public record DataAttack(boolean crit, boolean miss, int damage, int blockDamage, CombatEntity attacker) {

        @Override
        public String toString() {
            if (attacker instanceof Enemy) {
//                String string = attacker + " нанес вам " + damage + " ед. урона.";
                    String string = attacker.getName() + " нанес вам " + (damage - blockDamage) + " ед. урона. Вы заблокировали " + blockDamage + " ед. урона";
                if (miss()) {
                    return TextFormatter.of(attacker.getName() + " промахнулся.")
                            .colorize(TextFormatter.PURPLE)
                            .toString();
                } else if (crit()) {
                    return TextFormatter.of(attacker.getName() + " нанес критический удар.")
                            .colorize(TextFormatter.PURPLE)
                            + string;
                } else {
                    return string;
                }
            } else {
                String string = "Вы нанесли " + (damage - blockDamage) + " ед. урона. Заблокировано " + blockDamage + " ед. урона";
                if (miss()) {
                    return TextFormatter.of("Вы промахнулись").colorize(TextFormatter.PURPLE).toString();
                } else if (crit()) {
                    return TextFormatter.of("Критический удар.").colorize(TextFormatter.PURPLE)
                            + string;
                } else {
                    return string;
                }
            }
        }

    }
}
