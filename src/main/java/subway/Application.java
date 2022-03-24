package subway;

import subway.domain.LineRepository;
import subway.domain.StationRepository;
import subway.domain.User;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        LineRepository lineRepository = new LineRepository();
        StationRepository stationRepository = new StationRepository();
        View view = new View();
        User user = new User();
        subwayMap(scanner, lineRepository, stationRepository, view, user);
    }

    private static void subwayMap(Scanner scanner, LineRepository lineRepository, StationRepository stationRepository, View view, User user) {
        String input = "";
        Controller controller = new Controller();
        while (true) {
            view.showMainGuide();
            input = user.userInput(scanner);
            controller.startSubwayMap(input, scanner, user, lineRepository, stationRepository);
        }
    }
}
