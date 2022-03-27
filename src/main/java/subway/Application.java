package subway;

import subway.domain.LineRepository;
import subway.domain.StationRepository;
import subway.domain.User;

import java.util.Scanner;

public class Application {
    private static final int MAIN_MENU_OPTION_START = 1;
    private static final int MAIN_MENU_OPTION_END = 4;
    private static final String INITIAL_STRING_VARIABLE = "";
    private static final String QUIT = "Q";

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        User user = new User();

        subwayMap(scanner, user);
    }

    private static void subwayMap(Scanner scanner, User user) {
        LineRepository.initialLineRepository();
        StationRepository.initialStationRepository();
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
