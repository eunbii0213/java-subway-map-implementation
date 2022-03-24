package subway;

import subway.domain.*;

import java.util.Scanner;

public class Controller {
    private static final String OPTION_ONE = "1";
    private static final String OPTION_TWO = "2";
    private static final String OPTION_THREE = "3";
    private static final String OPTION_FOUR = "4";
    private static final String GO_BACK = "B";
    private static final int INITIAL_INDEX = 0;

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

            if (checker.checkUserInputIsNotValid(input, 1, 4)) {
                continue;
            }

            if (input.equals(GO_BACK)) {
                break;
            }
            if (input.equals(OPTION_ONE)) {
                //역 등록옵션
                stationOptionOne(stationView, checker, user, scanner, stationRepository);
                continue;
            }
            if (input.equals(OPTION_TWO)) {
                //역 삭제옵션
                stationOptionTwo(stationView, checker, user, scanner, stationRepository, lineRepository);
                continue;
            }
            if (input.equals(OPTION_THREE)) {
                //역 조회옵션
                stationView.showStationAllMessage(stationRepository);
                continue;
            }

        }//while문 끝
    }

    public void stationOptionOne(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository) {
        //역 등록옵션
        stationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);
        if (!checker.isSameName(userStationNameInput, stationRepository)) {
            stationRepository.addStation(new Station(userStationNameInput));
            stationView.showStationInsertComplete();
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
