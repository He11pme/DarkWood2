import text_styler.TextStyler;
import java.util.List;

public class DialogueEntry {
    public String id;
    public String speaker;
    public String text;
    public String type; // "input", "choice", etc.
    public List<Choice> choices;

    @Override
    public String toString() {
        String result;
        if (type.equals("choice")) {
            StringBuilder builder = new StringBuilder();
            for (Choice choice : choices) {
                builder.append(choice.text).append("\n");
            }
            result = builder.toString();
        } else if (speaker.equals("narrator")) {
            result = TextStyler.colorize(text, TextStyler.YELLOW);
        } else {
            result = speaker + ": " + text;
        }
        return result
                .replaceAll("\\[italic_on]", "\u001B[3m")
                .replaceAll("\\[italic_off]", "\u001B[0m");

    }


}