package subway;

import subway.domain.*;

import java.util.Scanner;

public class Controller {
    private static final String OPTION_ONE = "1";
    private static final String OPTION_TWO = "2";
    private static final String OPTION_THREE = "3";
    private static final String GO_BACK = "B";
    private static final int STATION_OPTION_START = 1;
    private static final int STATION_OPTION_END = 3;
    private static final int LINE_OPTION_START = 1;
    private static final int LINE_OPTION_END = 3;

    public void startSubwayMap(String input, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        if (input.equals(OPTION_ONE)) {
            stationManage(scanner, user, lineRepository, stationRepository);
        }
        if(input.equals(OPTION_TWO)){
            lineManage(scanner,user,lineRepository,stationRepository);
        }
    }

    public void lineManage(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        Checker checker = new Checker();
        LineView lineView = new LineView();
        lineView.showLineMenuGuide();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(GO_BACK)) {
                break;
            }

            if (checker.checkUserInputIsNotValid(input, LINE_OPTION_START, LINE_OPTION_END)) {
                lineView.showSelectGuideMessage();
                continue;
            }

            if (input.equals(OPTION_ONE)) {
                lineOptionOne(lineView, user, scanner, lineRepository,stationRepository);
                break;
            }
            if (input.equals(OPTION_TWO)) {
                lineOptionTwo(lineView, checker, user, scanner, stationRepository, lineRepository);
                break;
            }
            if (input.equals(OPTION_THREE)) {
                //stationView.showStationAllMessage(stationRepository);
                break;
            }
        }
    }

    public void lineOptionOne(LineView lineView, User user, Scanner scanner, LineRepository lineRepository, StationRepository stationRepository) {
        //노선 등록옵션
        lineView.showLineInsertNameGuide();

        String userLineNameInput = user.userInput(scanner);
        lineRepository.addLine(new Line(userLineNameInput));

        lineView.showInsertStartStationInLineGuide();
        String startStation = user.userInput(scanner);

        lineView.showInsertEndStationInLineGuide();
        String endStation = user.userInput(scanner);

        lineRepository.getListLines().get(lineRepository.lines().size()-1).addStationsInLine(startStation, endStation, stationRepository);

        lineRepository.addLine(new Line(userLineNameInput));
        lineView.showLineInsertComplete();
    }

    public void lineOptionTwo(LineView lineView, Checker checker, User user, Scanner scanner, StationRepository stationRepository, LineRepository lineRepository) {
        //노선 삭제옵션
        lineView.showLineRemoveGuide();
        String userRemoveLineInput = user.userInput(scanner);
        lineRepository.deleteLineByName(userRemoveLineInput);
        lineView.showLineRemoveComplete();

    }


    public void stationManage(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        Checker checker = new Checker();
        StationView stationView = new StationView();
        stationView.showStationMenuGuide();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(GO_BACK)) {
                break;
            }

            if (checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END)) {
                stationView.showSelectGuideMessage();
                continue;
            }
            if (input.equals(OPTION_ONE)) {
                stationOptionOne(stationView, checker, user, scanner, stationRepository);
                break;
            }
            if (input.equals(OPTION_TWO)) {
                stationOptionTwo(stationView, checker, user, scanner, stationRepository, lineRepository);
                break;
            }
            if (input.equals(OPTION_THREE)) {
                stationView.showStationAllMessage(stationRepository);
                break;
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
