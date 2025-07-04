package engine;

import creatures.Enemy;
import creatures.Fighter;
import text_styler.TextFormatter;

public class Attack extends Action {

    private final double attackFactor;
    private final double actionPointsFactor;
    private final double baseCritChance;
    private final double baseMissChance;
    private final int minStr;
    private final int minDex;

    private final static double CRIT_FACTOR = 1.5;
    private final static double DEX_FACTOR_CRIT = 0.5;
    private final static double DEX_FACTOR_MISS = 1.0;
    private final static double STR_FACTOR = 1.2;
    private final static double LEVEL_FACTOR_CRIT = 0.1;
    private final static double LEVEL_FACTOR_DAMAGE = 0.1;
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

    public void attack(Fighter attacker, Fighter opponent) {
        boolean crit = getCrit(attacker, opponent);
        boolean miss = getMiss(attacker, opponent);
        int damage = miss ? 0 : getDamage(crit, attacker, opponent);
        DataAttack dataAttack = new DataAttack(crit, miss, damage, attacker);
        opponent.takeDamage(damage);
        System.out.println(dataAttack);
    }

    public void run(Fighter attacker, Fighter opponent) {
        boolean crit = getCrit(attacker, opponent);
        boolean miss = getMiss(attacker, opponent);
        int damage = miss ? 0 : getDamage(crit, attacker, opponent);
        DataAttack dataAttack = new DataAttack(crit, miss, damage, attacker);
        opponent.takeDamage(damage);
        System.out.println(dataAttack);
    }


    private boolean getCrit(Fighter attacker, Fighter opponent) {

        double critChance =
                baseCritChance
                        + (attacker.getDex() - opponent.getDex()) * DEX_FACTOR_CRIT
                        + attacker.getLevel() * LEVEL_FACTOR_CRIT;

        if (critChance > MAX_CRIT_CHANCE) critChance = MAX_CRIT_CHANCE;
        return Math.random() * 100 < critChance;

    }

    private int getDamage(boolean crit, Fighter attacker, Fighter opponent) {
        int damage = (int) (attacker.getWeapon().getPower() * attackFactor
                        + attacker.getStr() * STR_FACTOR
                        + (1 + (attacker.getLevel() - opponent.getLevel()) * LEVEL_FACTOR_DAMAGE));

        return crit ? (int) (damage * CRIT_FACTOR) : damage;
    }

    private boolean getMiss(Fighter attacker, Fighter opponent) {

        double missChance = baseMissChance
                + (opponent.getDex() - attacker.getDex()) * DEX_FACTOR_MISS
                - attacker.getLevel() * LEVEL_FACTOR_MISS;

        if (missChance > MAX_MISS_CHANCE) missChance = MAX_MISS_CHANCE;
        return Math.random() * 100 < missChance;

    }

    @Override
    public double getActionPoints(Fighter attacker) {
        return attacker.getWeapon().getActionPoints() * actionPointsFactor;
    }

    @Override
    public String toString() {
        return getName();
    }

    public record DataAttack(boolean crit, boolean miss, int damage, Fighter attacker) {

        @Override
            public String toString() {
                if (attacker instanceof Enemy) {
                    String string = attacker + " нанес вам " + damage + " ед. урона.";
//                    String string = attacker.getName() + " нанес вам " + damage + " ед. урона.";
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
                    String string = "Вы нанесли " + damage + " ед. урона.";
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
