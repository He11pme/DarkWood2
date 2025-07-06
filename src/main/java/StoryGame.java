import com.fasterxml.jackson.core.type.TypeReference;
import engine.GameSession;
import living.entity.Merchant;
import living.entity.NPC;
import living.entity.Player;
import static story.dialogues.KeyResponse.*;

import engine.ListPotion;
import story.ReadFile;
import story.locations.Forest;
import story.locations.Location;
import story.locations.LocationNode;

public class StoryGame {

    Player player;
    NPC headman = new NPC("Фьор", "warden_talk", "intro");
    Merchant alchemist = new Merchant ("Морва", "alchemist_talk_gen_dial", "alchemist_talk_first_meeting", "alchemist_talk_hub", ListPotion.itemsOfAlchemist);

    Location village = new ReadFile().getData("village", new TypeReference<>() {});
    Forest forest = new Forest();
    public static void main(String[] args) {

        StoryGame storyGame = new StoryGame();
        storyGame.startStory();

    }

    private void startStory() {
        headman.startDialogue();
        player = createPlayer();
        GameSession.getInstance().setPlayer(player);
        LocationNode newNode = village.entranceToLocation(player);
        do {
            switch (newNode.getType()) {
                case "nextLocation" -> {
                    switch (newNode.getAction()) {
                        case "forest" -> {
                            if (forest.isInFirstForest()) {
                                forest.setInFirstForest(false);
                                forest.reset();
                                newNode = forest.entranceForest(player);
                            } else {
                                System.out.println("Вы устали и хотите отдохнуть. В лес вы не пойдете.");
                                newNode = village.entranceToLocation(player);
                            }
                        }
                        case "up", "left", "right", "down" -> newNode = forest.entranceForest(player);
                        case "village" -> newNode = village.entranceToLocation(player);
                    }
                }
                case "openDialogue" -> {
                    switch (newNode.getAction()) {
                        case "alchemist" -> alchemist.startDialogue();
                    }
                    newNode = village.entranceToLocation(player);
                }
                case "actionInLocation" -> {
                    switch (newNode.getAction()) {
                        case "next_day" -> {
                            player.levelUp();
                            forest.setInFirstForest(true);
                            newNode = village.entranceToLocation(player);
                        }
                    }
                }
            }
        } while (!newNode.getAction().equals("exitGame"));

    }

    private Player createPlayer() {
        String name = createPlayerResponse.get("name");
        Player player;
        switch (createPlayerResponse.get("career_choice")) {
            case "strength" -> player = new Player(name, 120, 1, 4);
            case "dexterity" -> player = new Player(name, 100, 7, 2);
            case "health" -> player = new Player(name, 140, 2, 1);
            default -> player = new Player(name, 100, 1, 1);
        }
        return player;
    }
}
