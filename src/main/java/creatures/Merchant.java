package creatures;

import com.fasterxml.jackson.core.type.TypeReference;
import engine.Item;
import engine.ListWeapon;
import story.ReadFile;
import story.dialogues.Dialogue;
import story.dialogues.DialogueNode;
import story.dialogues.ReadDialogue;
import text_styler.Reader;
import text_styler.TextFormatter;

import java.util.List;

public class Merchant extends NPC{

    private final Dialogue hubDialogue;
    private final List<? extends Item> items;

    public Merchant(String name, String jsonFilePathGenDial, String jsonFilePathFirstMeeting, String jsonFilePathHub, List<? extends Item> items) {
        super(name, jsonFilePathGenDial, jsonFilePathFirstMeeting);
        hubDialogue = new ReadFile().getData(jsonFilePathHub, new TypeReference<>() {});
        this.items = items;
    }

    public void startDialogue(Player player) {
        if (isFirstMeeting()) {
            new ReadDialogue().read(getFirstMeeting());
            setFirstMeeting(false);
        }
        outer: do {
            switch (new ReadDialogue().readHub(hubDialogue)) {
                case "buy" -> {
                    printItems();
                    int choice = Reader.readResponse(items.size());
                    if (player.getGold() >= items.get(choice).getCost()) {
                        player.spendGold(items.get(choice).getCost());
                        player.addItem(items.get(choice));
                        System.out.println("Успешно приобрели товар!");
                    } else {
                        System.out.println("Недостаточно денег!");
                    }
                }
                case "talk" -> new ReadDialogue().readDialogueWithNPC(getGeneralDialogue());
                case "exit" -> {
                    break outer;
                }
            }
        } while (true);

    }

    private void printItems() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Item item : items) {
            builder.append(++i)
                    .append(". ")
                    .append(item.toString());
        }
        System.out.println(builder.append("\n"));
    }
}
