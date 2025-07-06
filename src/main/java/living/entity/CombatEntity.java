package living.entity;

import engine.Action;
import engine.Weapon;

import java.util.List;

public abstract class CombatEntity extends LivingEntity {

    private int level = 1;
    private int agility;
    private int strength;
    private final int maxActionPoints;
    private double currentActionPoints;
    private int maxHealth;
    private int currentHealth;

    private int blockDamageReduction = 0;           // количество блокируемого урона
    private int dodgeChanceBonus = 0;               // насколько увеличивается шанс уклонения (в %)

    private boolean isAlive = true;                 // жив ли CombatEntity

    private Weapon equippedWeapon;

    private List<Action> allCombatActions;          // список действий во время битвы (атака, защита, зелье и побег)

    public CombatEntity(
            String name,
            int agility,
            int strength,
            int maxActionPoints,
            int maxHealth,
            Weapon equippedWeapon
    ) {
        super(name);
        this.agility = agility;
        this.strength = strength;
        this.maxActionPoints = maxActionPoints;
        this.currentActionPoints = maxActionPoints;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.equippedWeapon = equippedWeapon;
    }

    // методы работающие с уровнем
    public int getLevel() {
        return level;
    }

    public void increaseLevel() {
        maxHealth += 5;
        level++;
    }

    // Методы работающие с ловкостью
    public int getAgility() {
        return agility;
    }

    public void increaseAgility(int amount) {
        agility += amount;
    }

    // Методы работающие с силой
    public int getStrength() {
        return strength;
    }

    public void increaseStrength(int amount) {
        strength += amount;
    }

    // Методы работающие с ОД
    public int getMaxActionPoints() {
        return maxActionPoints + agility / 4;
    }

    public double getCurrentActionPoints() {
        return currentActionPoints;
    }

    public void spendActionPoints(double amount) {
        currentActionPoints -= amount;
    }

    public void restoreActionPoints() {
        currentActionPoints = getMaxActionPoints();
    }

    // Методы работающие со здоровьем
    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void takeDamage(int amount) {
        if (currentHealth > amount) currentHealth -= amount;
        else {
            isAlive = false;
            currentHealth = 0;
        }
    }

    public void heal(int amount) {
        currentHealth = Math.min(currentHealth + amount, maxHealth);
    }

    public void increaseHealth(int amount) {
        maxHealth += amount;
    }

    // методы работающие с модификаторами защиты
    public int getDodgeChanceBonus() {
        return dodgeChanceBonus;
    }

    public void setDodgeChanceBonus(int dodgeChanceBonus) {
        this.dodgeChanceBonus = dodgeChanceBonus;
    }

    public int getBlockDamageReduction() {
        return blockDamageReduction;
    }

    public void setBlockDamageReduction(int blockDamageReduction) {
        this.blockDamageReduction = blockDamageReduction;
    }

    public void resetDefenseModifiers() {
        blockDamageReduction = 0;
        dodgeChanceBonus = 0;
    }

    // геттер isAlive
    public boolean isAlive() {
        return isAlive;
    }

    // геттер equippedWeapon
    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    // методы работающие со списком действий во время сражения
    public List<Action> getAllCombatActions() {
        return allCombatActions;
    }

    public void setAllCombatActions(List<Action> allCombatActions) {
        this.allCombatActions = allCombatActions;
    }

    public abstract List<Action> getAvailableCombatActions();

}
