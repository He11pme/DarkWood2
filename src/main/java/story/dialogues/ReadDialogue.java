package story.dialogues;


import story.Manager;
import text_styler.TextFormatter;

import java.util.Collections;
import java.util.List;

import static story.dialogues.KeyResponse.*;

public class ReadDialogue implements Manager {

    public void read(List<Dialogue> dialogues) {

        for (Dialogue dial : dialogues) {
            String typeDialogue = dial.getType();
            System.out.print(replacePlaceholders(dial.toString()));
            switch (typeDialogue) {
                case "input" -> {
                    switch (dial.getId()) {
                        case "name" -> {
                            String name = scanner.nextLine();
                            keyResponse.put(dial.getId(), name);
                            createPlayerResponse.put(dial.getId(), name);
                        }
                    }
                }
                case "choice" -> {
                    DialogueNode choice = dial.getShowNodes().get(readResponse(dial.getShowNodes().size()));
                    keyResponse.put(dial.getId(), choice.getResponse());
                    switch (dial.getId()) {
                        case "career_choice" -> createPlayerResponse.put(dial.getId(), choice.getId());
                        case "history_choice" -> {}
                        default -> System.out.println(TextFormatter.of(dial.getSpeaker() + ": " +replacePlaceholders(choice.getResponse())).italicize().limitLengthLine());
                    }
                }
            }
        }

    }

    public void readDialogueWithNPC(Dialogue dial) {

        DialogueNode node;

        do {
            System.out.print(replacePlaceholders(dial.toString()));
            node = dial.getShowNodes().get(readResponse(dial.getShowNodes().size()));

            System.out.println(TextFormatter.of(dial.getSpeaker() + ": " + replacePlaceholders(node.getResponse()))
                    .limitLengthLine()
                    .italicize());

            //Открыть/закрыть выборы
            if (node.getNextNodes() != null) {
                for (DialogueNode ch : dial.getAllNodes()) {
                    if (ch.isUnlocked()) continue;
                    Collections.addAll(openNodes, node.getNextNodes());
                }
            }
            node.locked();

        } while (!node.getId().contains("exit"));

    }

    public String readHub(Dialogue dial) {
        DialogueNode node;
        System.out.print(replacePlaceholders(dial.toString()));
        node = dial.getShowNodes().get(readResponse(dial.getShowNodes().size()));
        System.out.println(TextFormatter.of(dial.getSpeaker() + ": " + replacePlaceholders(node.getResponse()))
                .limitLengthLine()
                .italicize());
        return node.getAction();
    }
}
