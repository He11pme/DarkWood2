package story.locations;

import com.fasterxml.jackson.core.type.TypeReference;
import creatures.Enemy;
import creatures.Spider;
import creatures.Wolf;
import creatures.Zombie;
import story.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdinaryForestLocation extends Location {

    private int line, column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public OrdinaryForestLocation(int line, int column) {
        this.line = line;
        this.column = column;
        if (!(line == 0 && column == 5)) fillEnemy();
        locationNodes = new ReadFile().getData("forest", new TypeReference<>() {});
        for (LocationNode node : locationNodes) {
            switch (node.getAction()) {
                case "up" -> {
                    if (line + 1 > 10) node.locked();
                }
                case "left" -> {
                    if (column - 1 < 0) node.locked();
                }
                case "right" -> {
                    if (column + 1 > 10) node.locked();
                }
                case "down" -> {
                    if (line - 1 < 0) node.locked();
                }
            }
        }
    }

    private String[] possibleEnemies = new String[] {"Spider", "Wolf", "Zombie"};
    private List<Enemy> enemies = new ArrayList<>();

    public List<Enemy> getEnemies() {
        return enemies;
    }

    private void fillEnemy() {
        String type = possibleEnemies[(int) (Math.random() * possibleEnemies.length)];
        int countEnemy = getCountEnemy();
        for (int k = 0; k < countEnemy; k++) {
            switch (type) {
                case "Spider" -> enemies.add(new Spider());
                case "Wolf" -> enemies.add(new Wolf());
                case "Zombie" -> enemies.add(new Zombie());
            }
        }
    }

    private int getCountEnemy() {
        return switch (line) {
            case 0, 1, 2 -> getWeightedRandom(0, 1, 2);
            case 3, 4, 5, 6, 7 -> getWeightedRandom(1, 2, 3);
            case 8, 9, 10 -> getWeightedRandom(2, 3, 3);
            default -> 0;
        };
    }

    private int getWeightedRandom(int... values) {
        final int[] weights = new int[]{55, 40, 5};
        if (values.length > weights.length) throw new IllegalArgumentException("Values size must be 3");
        int result = values[0];
        int rand = (int) (Math.random() * Arrays.stream(weights).sum());
        for (int i = 0; i < weights.length; i++) {
            if (rand < weights[i]) result = values[i];
            else rand -= weights[i];
        }

        return result;
    }




}
