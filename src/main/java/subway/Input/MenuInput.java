package subway.Input;

import java.util.Scanner;

public class MenuInput {
    private static final String GO_BACK = "B";
    private static final String QUIT = "Q";
    private static String userInput = "";

    public MenuInput(Scanner scanner, int optionStart, int optionEnd, boolean isMainMenu) {
        String nonCheckedInput = scanner.nextLine();
        checkInput(nonCheckedInput, optionStart, optionEnd, isMainMenu);
        userInput = nonCheckedInput;
    }

    public static String getUserInput() {
        return userInput;
    }

    public void checkInput(String userInput, int optionStart, int optionEnd, boolean isMainMenu) {
        int userInputToInt;
        try {
            userInputToInt = Integer.parseInt(userInput);
            if ((userInputToInt < optionStart || userInputToInt > optionEnd)) {
                throw new IllegalArgumentException("\n[ERROR] 선택할 수 없는 기능입니다.");
            }
        } catch (NumberFormatException e) {
            if (isMainMenu && !(userInput.equals(QUIT))) {
                throw new IllegalArgumentException("\n[ERROR] 선택할 수 없는 기능입니다.");
            }
            if (!isMainMenu && !userInput.equals(GO_BACK)) {
                throw new IllegalArgumentException("\n[ERROR] 선택할 수 없는 기능입니다.");
            }
        }
    }
}
