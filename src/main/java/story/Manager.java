package story;

import java.util.Scanner;

import static story.dialogues.KeyResponse.keyResponse;

public interface Manager {

    Scanner scanner = new Scanner(System.in);

    default int readResponse(int size) {

        String errString = "Пожалуйста, введите числовое значение от 1 до " + size;
        int input;

        System.out.print("Введите числовое значение от 1 до " + size + ": ");
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input > 0 && input <= size) return input - 1;
                else System.err.println(errString);
            } catch (NumberFormatException e) {
                System.err.println(errString);
            }
        }

    }

    default String replacePlaceholders(String text) {
        return text
                .replaceAll("\\{username}", keyResponse.get("name"));
    }

}
