package subway;

import subway.domain.*;

import java.util.Scanner;

public class Controller {
    private static final String OPTION_ONE = "1";
    private static final String OPTION_TWO = "2";
    private static final String OPTION_THREE = "3";
    private static final int STATION_OPTION_START = 1;
    private static final int STATION_OPTION_END = 4;
    private static final String GO_BACK = "B";

    public void startSubwayMap(String input, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        if (input.equals(OPTION_ONE)) {
            stationManage(scanner, user, lineRepository, stationRepository);
        }
    }

    public void stationManage(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        Checker checker = new Checker();

        while (true) {
            StationView stationView = new StationView();
            stationView.showStationMenuGuide();
            String input = scanner.nextLine();

            if (checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END)) {
                continue;
            }

            if (input.equals(GO_BACK)) {
                break;
            }
            if (input.equals(OPTION_ONE)) {
                stationOptionOne(stationView, checker, user, scanner, stationRepository);
                continue;
            }
            if (input.equals(OPTION_TWO)) {
                stationOptionTwo(stationView, checker, user, scanner, stationRepository, lineRepository);
                continue;
            }
            if (input.equals(OPTION_THREE)) {
                stationView.showStationAllMessage(stationRepository);
                continue;
            }
        }
    }

    public void stationOptionOne(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository) {
        //역 등록옵션
        stationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);
        if(checker.isLengthOverTwo(userStationNameInput)) {
            if (!checker.isSameName(userStationNameInput, stationRepository)) {
                stationRepository.addStation(new Station(userStationNameInput));
                stationView.showStationInsertComplete();
            }
        }
    }

    public void stationOptionTwo(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository, LineRepository lineRepository) {
        stationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);
        if (!checker.isContainStationInLine(lineRepository, userStationInput)) {
            stationRepository.deleteStation(userStationInput);
            stationView.showStationRemoveComplete();
        }
    }
}
