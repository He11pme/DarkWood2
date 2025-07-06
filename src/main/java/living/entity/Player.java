package living.entity;

import engine.*;
import text_styler.Reader;
import text_styler.TextFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends CombatEntity {

    private int gold = 10;
    private int exp = 0;
    private final Map<Item, Integer> inventory = new HashMap<>();

    private final List<AttributeIncreaseOption> attributeIncreaseOptions =
            List.of(ListAttributeIncreaseOption.HEALTH_INCREASE_OPTION,
                    ListAttributeIncreaseOption.STRENGTH_INCREASE_OPTION,
                    ListAttributeIncreaseOption.AGILITY_INCREASE_OPTION);

    public Player(String name, int agility, int strength, int maxHealth) {
        super(name, agility, strength,  2, maxHealth,   ListWeapon.SWORD);
        setAllCombatActions(new ArrayList<>(List.of(
                ListAttack.BASIC, ListAttack.WHIRL, ListAttack.REND,        // атакующие действия
                ListDefence.IMPROVE_BLOCK, ListDefence.IMPROVE_DODGE,       // защитные действия
                ListPotion.USE_POTION_HEALTH,                               // использование предмета
                ListDefence.TRY_ESCAPE)));                                  // прочие действия (побег и т.д.)
    }

    //  ===========================
    //  Методы работающие с уровнем
    //  и аттрибутами (health,
    //  strength, agility)
    //  ===========================

    private boolean canLevelUp() {
        return exp / 100 > getLevel() - 1;
    }

    public void levelUp() {
        boolean leveledUp;
        do {
            if (canLevelUp()) {
                System.out.println(TextFormatter.of("Вы сидите у костра и понимаете, что за сегодня вы: "));

                displayAttributeIncreaseOptions();
                applyAttributeIncrease();

                increaseLevel();

                leveledUp = true;
            } else leveledUp = false;
        } while (leveledUp);
    }

    private void displayAttributeIncreaseOptions() {
        System.out.println(buildAttributeIncreaseOptionsString());
    }

    private String buildAttributeIncreaseOptionsString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < attributeIncreaseOptions.size(); i++) {
            builder.append(i + 1)
                    .append(". ")
                    .append(attributeIncreaseOptions.get(i))
                    .append("\n");
        }
        return builder.toString();
    }

    private void applyAttributeIncrease() {
        int index = Reader.readResponse(attributeIncreaseOptions.size());
        AttributeIncreaseOption selectedOption = attributeIncreaseOptions.get(index);
        int increaseAmount = selectedOption.getIncreaseAmount();

        switch (selectedOption.getAttribute()) {
            case HP -> increaseHealth(increaseAmount);
            case STRENGTH -> increaseStrength(increaseAmount);
            case AGILITY -> increaseAgility(increaseAmount);
        }
    }

    //  ========================
    //  Методы работающие с gold
    //  ========================

    public void addGold(int amount) {
        gold += amount;
    }

    public boolean spendGold(int amount) {
        if (gold < amount) return false;
        else {
            gold -= amount;
            return true;
        }
    }

    //  =======================
    //  Методы работающие с exp
    //  =======================

    public void addExp(int amount) {
        exp += amount;
        if (canLevelUp()) {
            System.out.println(TextFormatter.of("Отдохните в деревне, чтобы повысить уровень.")
                    .colorize(TextFormatter.GREEN));
        }
    }

    //  ==============================
    //  Методы работающие с инвентарем
    //  ==============================

    public void addItemToInventory(Item item) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + 1);
        } else inventory.put(item, 1);
    }

    public void useItem(Item item) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) - 1);
        } else {
            System.out.println("Такого предмета нет");
        }
    }

    //  ====================================
    //  Методы работающие с allCombatActions
    //  ====================================

    @Override
    public List<Action> getAvailableCombatActions() {
        List<Action> availableCombatActions = new ArrayList<>();
        for (Action combatAction : getAllCombatActions()) {
            if (combatAction instanceof Attack attack) {
                if (getStrength() >= attack.getMinStr() && getAgility() >= attack.getMinDex()) {
                    availableCombatActions.add(attack);
                }
            } else if (combatAction instanceof UsePotion usePotion) {
                if (inventory.containsKey(usePotion.getPotion()) && inventory.get(usePotion.getPotion()) > 0) {
                    availableCombatActions.add(usePotion);
                }
            } else {
                availableCombatActions.add(combatAction);
            }
        }
        return availableCombatActions;
    }

    @Override
    public String toString() {
        return getName() +
                ": HP = " + getCurrentHealth() + "/" + getMaxHealth() +
                "; ОД = " + getCurrentActionPoints() + "/" + getMaxActionPoints();
    }

}
