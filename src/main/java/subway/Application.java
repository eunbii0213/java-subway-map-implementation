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
        View view = new View();
        User user = new User();
        Checker checker = new Checker();

        subwayMap(scanner, checker, view, user);
    }

    private static void subwayMap(Scanner scanner, Checker checker, View view, User user) {
        LineRepository.initialLineRepository();
        StationRepository.initialStationRepository();
        Controller controller = new Controller();
        LineView lineView = new LineView();
        SectionView sectionView = new SectionView();
        StationView stationView = new StationView();

        String input = INITIAL_STRING_VARIABLE;
        while (!input.equals(QUIT)) {
            view.showMainGuide();
            input = user.userInput(scanner);
            if (!checker.checkUserInputIsNotValid(input, MAIN_MENU_OPTION_START, MAIN_MENU_OPTION_END, true)) {
                controller.startSubwayMap(stationView, lineView, view, sectionView, input, checker, scanner, user);
            }
        }
    }
}
