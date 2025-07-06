package story.dialogues;

import text_styler.TextFormatter;
import static story.dialogues.KeyResponse.*;

import java.util.ArrayList;
import java.util.List;

public class Dialogue {
    private String id = "404";
    private String speaker;
    private String text;
    private String type; // "input", "choice", etc.
    private List<DialogueNode> allNodes;
    private List<DialogueNode> showNodes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getType() {
        return type;
    }

    public List<DialogueNode> getAllNodes() {
        return allNodes;
    }

    public List<DialogueNode> getShowNodes() {
        return showNodes;
    }

    @Override
    public String toString() {
        String result;
        if (type.equals("choice") || type.equals("cyclic_choice")) {
            StringBuilder builder = new StringBuilder("\n");

            int i = 0;
            showNodes.clear();
            for (DialogueNode dialogueNode : allNodes) {
                if ((dialogueNode.isUnlocked() || openNodes.contains(dialogueNode.getId())) && dialogueNode.isFirst()) {
                    showNodes.add(dialogueNode);
                    builder
                            .append(++i)
                            .append(". ")
                            .append(TextFormatter.of(dialogueNode.getText())
                                    .limitLengthLine());
                }
            }

            result = builder.append("\n").toString();
        } else if (type.equals("input")) {
            result = TextFormatter.of(replacePlaceholders(text)).limitLengthLine().toString();
        } else if (speaker.equals("narrator")) {
            result = TextFormatter.of(replacePlaceholders(text))
                    .limitLengthLine()
                    .colorize(TextFormatter.YELLOW)
                    .toString();
        } else {
            result = speaker + ": " + TextFormatter.of(replacePlaceholders(text)).limitLengthLine();
        }

        return TextFormatter.of(result).italicize().toString();

    }

    private String replacePlaceholders(String text) {
        return text
                .replaceAll("\\[response_history_choice]", keyResponse.get("history_choice"))
                .replaceAll("\\[response_career_choice]", keyResponse.get("career_choice"));
    }




}