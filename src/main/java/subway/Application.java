package subway;

import subway.domain.LineRepository;
import subway.domain.Section;
import subway.domain.StationRepository;
import subway.domain.User;

import java.util.Scanner;

public class Application {
    private static final int MAIN_MENU_OPTION_START = 1;
    private static final int MAIN_MENU_OPTION_END = 4;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        LineRepository lineRepository = new LineRepository();
        StationRepository stationRepository = new StationRepository();
        View view = new View();
        User user = new User();
        Checker checker = new Checker();

        subwayMap(scanner, checker,lineRepository, stationRepository, view, user);
    }

    private static void subwayMap(Scanner scanner, Checker checker, LineRepository lineRepository, StationRepository stationRepository, View view, User user) {
        String input = "";
        Controller controller = new Controller();
        LineView lineView = new LineView();
        SectionView sectionView = new SectionView();
        Section section = new Section();
        StationView stationView = new StationView();

        while (true) {
            view.showMainGuide();
            input = user.userInput(scanner);
            checker.checkUserInputIsNotValid(input, MAIN_MENU_OPTION_START, MAIN_MENU_OPTION_END);
            controller.startSubwayMap(stationView,lineView,view,sectionView,section,input, checker,scanner, user, lineRepository, stationRepository);
        }
    }
}
