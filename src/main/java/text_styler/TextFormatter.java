package text_styler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormatter {
    private String text;

    private TextFormatter(String text) {
        this.text = text;
    }

    public static TextFormatter of(String text) {
        return new TextFormatter(text);
    }

    // ANSI коды для цветов
    private static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private static final int MAX_LENGTH = 150;

    public TextFormatter colorize(String color) {
        text = text.replace(RESET, RESET + color);
        text = color + text + RESET;
        return this;
    }

    public TextFormatter italicize() {
        text = text
                .replace("[italic_on]", "\u001B[3m")
                .replace("[italic_off]", "\u001B[0m");
        return this;
    }

    public TextFormatter limitLengthLine() {

        if (text.length() > MAX_LENGTH) {
            StringBuilder builder = new StringBuilder();
            Pattern pattern = Pattern.compile("(?s).{1," + MAX_LENGTH + "}(?=\\s|$)");
            Matcher matcher = pattern.matcher(text);

            //Надо пересмотреть данный цикл
            int lastEnd = 0;
            while (matcher.find(lastEnd)) {
                String part = matcher.group().trim();
                builder.append(part).append("\n");
                lastEnd = matcher.end();
            }

            text = builder.toString();
            return this;
        }
        return this;
    }

    @Override
    public String toString() {
        return text;
    }
}
