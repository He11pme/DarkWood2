package story.locations;

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

    public LocationNode entranceToLocation() {
        System.out.println(getDescription());
        printNodes();
        return getNode();
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

    LocationNode getNode() {
        return showLocationNodes.get(readResponse(showLocationNodes.size()));
    }

}
