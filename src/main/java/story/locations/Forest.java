package story.locations;

import com.fasterxml.jackson.core.type.TypeReference;
import living.entity.Player;
import story.ReadFile;

public class Forest {

    private OrdinaryForestLocation[][] mapForest = new OrdinaryForestLocation[11][11];
    private OrdinaryForestLocation currentLocation;
    private String[] descriptionForestLocation;
    private boolean inFirstForest = true;

    public Forest() {
        descriptionForestLocation = new ReadFile().getData("description_forest_location", new TypeReference<>() {});
        fillForest();
        currentLocation = mapForest[0][5];
    }

    public boolean isInFirstForest() {
        return inFirstForest;
    }

    public void setInFirstForest(boolean inFirstForest) {
        this.inFirstForest = inFirstForest;
    }

    public LocationNode entranceForest(Player player) {
        LocationNode currentNode = currentLocation.entranceToLocation(player);

        switch (currentNode.getAction()) {
            case "up" -> currentLocation = mapForest[currentLocation.getLine() + 1][currentLocation.getColumn()];
            case "left" -> currentLocation = mapForest[currentLocation.getLine()][currentLocation.getColumn() - 1];
            case "right" -> currentLocation = mapForest[currentLocation.getLine()][currentLocation.getColumn() + 1];
            case "down" -> currentLocation = mapForest[currentLocation.getLine() - 1][currentLocation.getColumn()];
            default -> currentLocation = mapForest[0][5];
        }

        return currentNode;
    }

    public void reset() {
        fillForest();
        currentLocation = mapForest[0][5];
    }

    private void fillForest() {
        for (int i = 0; i < mapForest.length; i++) {
            for (int j = 0; j < mapForest[i].length; j++) {
                OrdinaryForestLocation forestLocation = new OrdinaryForestLocation(i, j);
                forestLocation.setFirstDescription(descriptionForestLocation[(int) (Math.random() * descriptionForestLocation.length)]);
                mapForest[i][j] = forestLocation;
            }
        }
    }
}
