package story.locations;

import creatures.Enemy;
import creatures.Player;
import engine.Fight;

import java.util.List;

public class Forest {

    private OrdinaryForestLocation[][] mapForest = new OrdinaryForestLocation[11][11];
    private OrdinaryForestLocation currentLocation;

    public Forest() {
        for (int i = 0; i < mapForest.length; i++) {
            for (int j = 0; j < mapForest[i].length; j++) {
                mapForest[i][j] = new OrdinaryForestLocation(i, j);
            }
        }
        currentLocation = mapForest[0][5];
    }

    public LocationNode entranceForest(Player player) {

        if (!currentLocation.getEnemies().isEmpty()) {
            if (new Fight(currentLocation.getEnemies(), player).startFight()) {
                System.out.println("Вы победили");
                currentLocation.printNodes();
            } else {
                System.out.println("Вы проиграли");
            }
        } else currentLocation.printNodes();



        LocationNode currentNode = currentLocation.getNode();
        switch (currentNode.getAction()) {
            case "up" -> currentLocation = mapForest[currentLocation.getLine() + 1][currentLocation.getColumn()];
            case "left" -> currentLocation = mapForest[currentLocation.getLine()][currentLocation.getColumn() - 1];
            case "right" -> currentLocation = mapForest[currentLocation.getLine()][currentLocation.getColumn() + 1];
            case "down" -> currentLocation = mapForest[currentLocation.getLine() - 1][currentLocation.getColumn()];
            default -> currentLocation = mapForest[0][5];
        }

        return currentNode;
    }




}
