package creatures;

import engine.*;
import story.locations.LocationNode;
import text_styler.Reader;
import text_styler.TextFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Fighter {

    private int gold = 10;
    private int exp = 0;
    private Map<Item, Integer> inventory = new HashMap<>();

    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

    public Player(String name, int maxHealth, int dex, int str) {
        super(name, maxHealth, dex, str, 2, ListWeapon.SWORD);
        actionInFight = new ArrayList<>(List.of(
                ListAttack.BASIC, ListAttack.WHIRL, ListAttack.REND,
                ListDefence.IMPROVE_BLOCK, ListDefence.IMPROVE_DODGE,
                ListPotion.USE_POTION_HEALTH,
                ListDefence.TRY_ESCAPE));
    }

    private boolean checkUpLevel() {
        return exp / 100 > level - 1;
    }

    public void levelUp() {
        boolean upper;
        do {
            if (checkUpLevel()) {
                String[] improveParameter = new String[] {"Укрепили здоровье", "Стали сильнее", "Улучшили ловкость"};
                System.out.println(TextFormatter.of("Вы сидите у костра и понимаете, что за сегодня вы: "));
                int i = 0;
                StringBuilder builder = new StringBuilder("\n");

                for (String improve : improveParameter) {
                    builder
                            .append(++i)
                            .append(". ")
                            .append(TextFormatter.of(improve)
                                    .limitLengthLine())
                            .append("\n");
                }

                System.out.println(builder);
                switch (Reader.readResponse(improveParameter.length)) {
                    case 0 -> this.maxHealth += 15;
                    case 1 -> this.str += 1;
                    case 2 -> this.dex += 1;
                }
                this.maxHealth += 5;
                level += 1;
                upper = true;
            } else upper = false;
        } while (upper);

    }

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

    public void addExp(int amount) {
        exp += amount;
        if (checkUpLevel()) {
            System.out.println(TextFormatter.of("Отдохните в деревне, чтобы повысить уровень.")
                    .colorize(TextFormatter.GREEN));
        }
    }

    public void addItem(Item item) {
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

    @Override
    public List<Action> getActionInFight() {
        List<Action> availableActions = new ArrayList<>();
        for (Action action : actionInFight) {
            if (action instanceof Attack attack) {
                if (getStr() >= attack.getMinStr() && getDex() >= attack.getMinDex()) {
                    availableActions.add(attack);
                }
            } else if (action instanceof UsePotion usePotion) {
                if (inventory.containsKey(usePotion.getPotion()) && inventory.get(usePotion.getPotion()) > 0) {
                    availableActions.add(usePotion);
                }
            } else {
                availableActions.add(action);
            }
        }
        return availableActions;
    }

    @Override
    public String toString() {
        return "Player{" +
                "gold=" + gold +
                ", exp=" + exp +
                ", maxHealth=" + maxHealth +
                ", dex=" + dex +
                ", str=" + str +
                ", actionPoints=" + actionPoints +
                ", maxActionPoints=" + maxActionPoints +
                ", level=" + level +
                '}';
    }

    //    @Override
//    public String toString() {
//        return getName() + ": HP = " + getHealth() + "/" + getMaxHealth() + "; Action points = " + getActionPoints() + "; Str = " + getStr() + "; Dex = " + getDex() + "; Weapon: " + getWeapon().getName();
//    }

}
