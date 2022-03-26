package subway;

import subway.domain.*;
import java.util.Scanner;

public class Controller {
    private static final String OPTION_ONE = "1";
    private static final String OPTION_TWO = "2";
    private static final String OPTION_THREE = "3";
    private static final String OPTION_FOUR = "4";
    private static final String GO_BACK = "B";
    private static final int STATION_OPTION_START = 1;
    private static final int STATION_OPTION_END = 3;
    private static final int LINE_OPTION_START = 1;
    private static final int LINE_OPTION_END = 3;
    private static final int SECTION_OPTION_START = 1;
    private static final int SECTION_OPTION_END = 2;
    private static final String INITIAL_STRING_VARIABLE = "";

    public void startSubwayMap(StationView stationView, LineView lineView, View view, SectionView sectionView, String input, Checker checker, Scanner scanner, User user) {
        if (input.equals(OPTION_ONE)) {
            stationView.showStationMenuGuide();
            stationManage(true, INITIAL_STRING_VARIABLE, checker, stationView, scanner, user);
        }
        if (input.equals(OPTION_TWO)) {
            lineView.showLineMenuGuide();
            lineManage(true, INITIAL_STRING_VARIABLE, checker, lineView, scanner, user);
        }
        if (input.equals(OPTION_THREE)) {
            sectionView.showSectorMenuGuide();
            sectionManage(true, INITIAL_STRING_VARIABLE, checker, sectionView, scanner, user);
        }
        if (input.equals(OPTION_FOUR)) {
            view.printAllLineAndStationInfo();
        }
    }

    public boolean isSectionOptionOneError(Scanner scanner, User user, SectionView sectionView) {
        sectionView.showSectorInsertGuide();
        String userInputLineName = user.userInput(scanner);
        Line line = Section.findLineIndexFromLines(userInputLineName);

        sectionView.showSectorInsertStationNameGuide();
        String userInputStationName = user.userInput(scanner);
        int stationIndex = Section.findStationIndexFromStations(userInputStationName);
        Station station = StationRepository.getStations().get(stationIndex);

        sectionView.showSectorInsertNumberGuide();
        String userInputIndex = user.userInput(scanner);

        int index = Integer.parseInt(userInputIndex) - 1;
        Section.addStationInLines(index, line, station);

        sectionView.showSectorInsertComplete();
        return false;
    }

    public boolean isSectionOptionTwoError(Scanner scanner, User user, SectionView sectionView, Checker checker) {
        sectionView.showSectorRemoveLineGuide();
        Line line = Section.findLineIndexFromLines(user.userInput(scanner));

        sectionView.showSectorRemoveStationGuide();
        int stationIndex = Section.findStationIndexFromStations(user.userInput(scanner));
        Station station = StationRepository.getStations().get(stationIndex);
        int index = line.searchTargetStationIndexInSubwayMap(station.getName());

        if (sectionCheck(checker, line)) {
            return true;
        }

        line.removeStation(index--);

        if (checker.isLastStation(line, index, stationIndex)) {
            line.changeEndStationAfterRemoveEndStation(stationIndex);
        }

        sectionView.showSectorRemoveComplete();
        return false;
    }

    public boolean sectionCheck(Checker checker, Line line) {
        if (!checker.isLineSizeOverTwo(line)) {
            return true;
        }
        return false;
    }

    public void sectionManage(boolean getUserInputAgain, String input, Checker checker, SectionView sectionView, Scanner scanner, User user) {
        while (!input.equals(GO_BACK) && getUserInputAgain) {
            sectionView.showSelectGuideMessage();
            input = user.userInput(scanner);

            getUserInputAgain = checker.checkUserInputIsNotValid(input, SECTION_OPTION_START, SECTION_OPTION_END, false);

            if (input.equals(OPTION_ONE)) {
                getUserInputAgain = isSectionOptionOneError(scanner, user, sectionView);
            }
            if (input.equals(OPTION_TWO)) {
                getUserInputAgain = isSectionOptionTwoError(scanner, user, sectionView, checker);
            }
        }
    }

    public void lineManage(boolean getUserInputAgain, String input, Checker checker, LineView lineView, Scanner scanner, User user) {
        while (!input.equals(GO_BACK) && getUserInputAgain) {
            lineView.showSelectGuideMessage();
            input = user.userInput(scanner);
            getUserInputAgain = checker.checkUserInputIsNotValid(input, LINE_OPTION_START, LINE_OPTION_END, false);

            if (input.equals(OPTION_ONE)) {
                getUserInputAgain = isLineOptionOneError(lineView, checker, user, scanner);
            }
            if (input.equals(OPTION_TWO)) {
                getUserInputAgain = isLineOptionTwoError(lineView, user, scanner);
            }
            if (input.equals(OPTION_THREE)) {
                getUserInputAgain = lineView.showLineAllMessage();
            }
        }
    }

    public boolean isLineOptionOneError(LineView lineView, Checker checker, User user, Scanner scanner) {
        lineView.showLineInsertNameGuide();
        String userLineNameInput = user.userInput(scanner);

        if (!checker.isLengthOverTwo(userLineNameInput) || checker.isSameLine(userLineNameInput)) {
            lineView.showLineMenuGuide();
            return true;
        }

        LineRepository.addLine(new Line(userLineNameInput));

        lineView.showInsertStartStationInLineGuide();
        String startStation = user.userInput(scanner);
        lineView.showInsertEndStationInLineGuide();
        String endStation = user.userInput(scanner);

        LineRepository.getListLines().get(LineRepository.lines().size() - 1).addStationsInLine(startStation, endStation);

        lineView.showLineInsertComplete();
        return false;
    }

    public boolean isLineOptionTwoError(LineView lineView, User user, Scanner scanner) {
        lineView.showLineRemoveGuide();
        String userRemoveLineInput = user.userInput(scanner);
        LineRepository.deleteLineByName(userRemoveLineInput);
        lineView.showLineRemoveComplete();

        return false;
    }

    public void stationManage(boolean getUserInputAgain, String input, Checker checker, StationView stationView, Scanner scanner, User user) {
        while (!input.equals(GO_BACK) && getUserInputAgain) {
            stationView.showSelectGuideMessage();
            input = scanner.nextLine();

            getUserInputAgain = checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END, false);

            if (input.equals(OPTION_ONE)) {
                getUserInputAgain = isStationOptionOneError(stationView, checker, user, scanner);
            }
            if (input.equals(OPTION_TWO)) {
                getUserInputAgain = isStationOptionTwoError(stationView, checker, user, scanner);
            }
            if (input.equals(OPTION_THREE)) {
                getUserInputAgain = stationView.showStationAllMessage();
            }
        }
    }

    public boolean isStationOptionOneError(StationView stationView, Checker checker, User user, Scanner scanner) {
        stationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);

        if (checker.isLengthOverTwo(userStationNameInput)) {
            if (!checker.isSameName(userStationNameInput)) {
                StationRepository.addStation(new Station(userStationNameInput));
                stationView.showStationInsertComplete();
                return false;
            }
        }
        return true;
    }

    public boolean isStationOptionTwoError(StationView stationView, Checker checker, User user, Scanner scanner) {
        stationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);
        if (!checker.isContainStationInLine(userStationInput)) {
            StationRepository.deleteStation(userStationInput);
            stationView.showStationRemoveComplete();
            return false;
        }
        return true;
    }
}
