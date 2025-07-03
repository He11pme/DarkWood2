import com.fasterxml.jackson.core.type.TypeReference;
import creatures.NPC;
import creatures.Player;
import static story.dialogues.KeyResponse.*;

import engine.ListWeapon;
import story.ReadFile;
import story.locations.Forest;
import story.locations.Location;
import story.locations.LocationNode;
import text_styler.TextFormatter;

public class StoryGame {

    Player player = new Player("Ivan", 500, 4, 1);
    NPC headman = new NPC("Фьор", "warden_talk", "intro");
//    NPC alchemist = new NPC("Морва", 200, 10, 2, "alchemist_talk");

    Location village = new ReadFile().getData("village", new TypeReference<>() {});
    Forest forest = new Forest();
    public static void main(String[] args) {

        StoryGame storyGame = new StoryGame();
        storyGame.startStory();

    }

    private void startStory() {
        headman.startDialogue();
        player = createPlayer();
        LocationNode newNode = village.entranceToLocation();
        do {
            switch (newNode.getType()) {
                case "nextLocation" -> {
                    switch (newNode.getAction()) {
                        case "forest", "up", "left", "right", "down" -> newNode = forest.entranceForest(player);
                        case "village" -> newNode = village.entranceToLocation();
                    }
                }
                case "openDialogue" -> {

                }
                case "actionInLocation" -> {

                }
            }
        } while (!newNode.getAction().equals("exitGame"));

    }

    private Player createPlayer() {
        String name = createPlayerResponse.get("name");
        Player player;
        switch (createPlayerResponse.get("career_choice")) {
            case "strength" -> player = new Player(name, 500, 2, 7);
            case "dexterity" -> player = new Player(name, 10, 4, 2);
            case "health" -> player = new Player(name, 40, 2, 1);
            default -> player = new Player(name, 10, 1, 1);
        }
        return player;
    }
}
