package subway;

import subway.domain.InitialBeforeStart;
import subway.domain.User;

import java.util.Scanner;

public class Application {
    private static final int MAIN_MENU_OPTION_START = 1;
    private static final int MAIN_MENU_OPTION_END = 4;
    private static final String INITIAL_STRING_VARIABLE = "";
    private static final String QUIT = "Q";

    public static void main(String[] args) {
        startSubwayProgram();
    }

    public static void startSubwayProgram() {
        subwayMap(new InitialBeforeStart());
    }

    private static void subwayMap(InitialBeforeStart initialBeforeStart) {
        initialBeforeStart.initialLineRepository();
        initialBeforeStart.initialStationRepository();

        subwayProgramStart();
    }

    public static void subwayProgramStart() {
        final Scanner scanner = new Scanner(System.in);
        User user = new User();
        Controller controller = new Controller();
        String input = INITIAL_STRING_VARIABLE;

        while (!input.equals(QUIT)) {
            View.showMainGuide();
            input = user.userInput(scanner);
            if (!Checker.checkUserInputIsNotValid(input, MAIN_MENU_OPTION_START, MAIN_MENU_OPTION_END, true)) {
                controller.startSubwayMap(input, scanner, user);
            }
        }
    }
}
