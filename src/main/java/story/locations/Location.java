package story.locations;

import creatures.Fighter;
import creatures.Player;
import engine.Fight;
import story.Manager;
import text_styler.TextFormatter;

import java.util.ArrayList;
import java.util.List;

public class Location implements Manager {

    private boolean isFirstEntrance = true;
    private String firstDescription;
    private String generalDescription;
    List<LocationNode> locationNodes;
    List<LocationNode> showLocationNodes = new ArrayList<>();

    public void setFirstDescription(String firstDescription) {
        this.firstDescription = firstDescription;
    }

    public LocationNode entranceToLocation(Player player) {
        System.out.println(getDescription());
        if (this instanceof OrdinaryForestLocation forestLocation) {
            if (!forestLocation.getEnemies().isEmpty()) {
                if (new Fight(forestLocation.getEnemies(), player).startFight()) {
                    if (forestLocation.getEnemies().stream().anyMatch(Fighter::isLive)) {
                        System.out.println("Вы сбежали");
                        return locationNodes.getLast();
                    } else {
                        System.out.println("Вы победили");
                        printNodes();
                    }
                } else {
                    System.out.println("Вы проиграли");
                }
            } else printNodes();
        } else printNodes();
        return showLocationNodes.get(readResponse(showLocationNodes.size()));
    }

    private String getDescription() {
        String description;
        if (isFirstEntrance && firstDescription != null) {
            description = firstDescription;
            isFirstEntrance = false;
        } else {
            description = generalDescription;
        }
        return TextFormatter.of(description)
                .limitLengthLine()
                .italicize()
                .colorize(TextFormatter.YELLOW)
                .toString();
    }

    void printNodes() {
        StringBuilder builder = new StringBuilder("\n");

        showLocationNodes.clear();
        int i = 0;
        for (LocationNode node : locationNodes) {
            if (!node.isUnlocked()) continue;
            showLocationNodes.add(node);
            builder
                    .append(++i)
                    .append(". ")
                    .append(TextFormatter.of(node.getText())
                            .limitLengthLine());
        }

        System.out.println(builder);
    }
}
