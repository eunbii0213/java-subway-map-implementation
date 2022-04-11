package subway;

import subway.Input.MenuInput;
import subway.view.View;

import java.util.Scanner;

public class Application {
    private static final int MAIN_MENU_OPTION_START = 1;
    private static final int MAIN_MENU_OPTION_END = 4;
    private static final String INITIALIZE_STRING_VARIABLE = "";
    private static final String QUIT = "Q";

    public static void main(String[] args) {
        InitializeAndProgramStart();
    }

    private static void InitializeAndProgramStart() {
        Initialization initialization = new Initialization();
        initialization.initializeStationRepository();
        initialization.initializeLineRepository();

        subwayProgramStart();
    }

    public static void subwayProgramStart() {
        final Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String userInput = INITIALIZE_STRING_VARIABLE;

        View.showMainGuide();
        while (!userInput.equals(QUIT)) {
            try {
                View.showSelectGuideMessage();
                MenuInput menuInput = new MenuInput(scanner, MAIN_MENU_OPTION_START, MAIN_MENU_OPTION_END, true);
                userInput = menuInput.getUserInput();
                controller.startSubwayMap(userInput, scanner);
            } catch (Exception e) {
                View.showExceptionMessage(e);
                continue;
            }
            if (!userInput.equals(QUIT)) {
                View.showMainGuide();
            }
        }
    }
}
