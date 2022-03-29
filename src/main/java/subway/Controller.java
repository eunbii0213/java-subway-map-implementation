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
    private static final int LAST_INDEX_CONSTANT = 1;

    public void startSubwayMap(String input, Scanner scanner, User user) {
        if (input.equals(OPTION_ONE)) {
            StationView.showStationMenuGuide();
            stationManage(scanner, user);
        }
        if (input.equals(OPTION_TWO)) {
            LineView.showLineMenuGuide();
            lineManage(scanner, user);
        }
        if (input.equals(OPTION_THREE)) {
            SectionView.showSectorMenuGuide();
            sectionManage(scanner, user);
        }
        if (input.equals(OPTION_FOUR)) {
            View.printAllLineAndStationInfo();
        }
    }

    public boolean isSectionOptionOneError(Scanner scanner, User user) {
        addSectionInLines(scanner, user);
        return false;
    }

    public void addSectionInLines(Scanner scanner, User user) {
        SectionView.showSectorInsertGuide();
        String userInputLineName = user.userInput(scanner);
        Line line = Section.findLineIndexFromLines(userInputLineName);

        SectionView.showSectorInsertStationNameGuide();
        String userInputStationName = user.userInput(scanner);
        int stationIndex = Section.findStationIndexFromStations(userInputStationName);
        Station station = StationRepository.stations().get(stationIndex);

        SectionView.showSectorInsertNumberGuide();
        String userInputIndex = user.userInput(scanner);

        int index = Integer.parseInt(userInputIndex) - LAST_INDEX_CONSTANT;
        line.addStationInLine(index, station);

        SectionView.showSectorInsertComplete();
    }

    public boolean isSectionOptionTwoError(Scanner scanner, User user) {
        SectionView.showSectorRemoveLineGuide();
        Line line = Section.findLineIndexFromLines(user.userInput(scanner));

        if (sectionCheck(line)) {
            return true;
        }
        removeStationInLine(line, user, scanner);
        return false;
    }

    public void removeStationInLine(Line line, User user, Scanner scanner) {
        SectionView.showSectorRemoveStationGuide();
        int stationIndex = Section.findStationIndexFromStations(user.userInput(scanner));
        Station station = StationRepository.stations().get(stationIndex);
        int index = line.searchTargetStationIndexInSubwayMap(station.getName());

        line.removeStation(index);
        SectionView.showSectorRemoveComplete();
    }

    public boolean sectionCheck(Line line) {
        if (!Checker.isLineSizeOverTwo(line)) {
            return true;
        }
        return false;
    }

    public void sectionManage(Scanner scanner, User user) {
        boolean isGetUserInput = true;
        String input = INITIAL_STRING_VARIABLE;

        while (!input.equals(GO_BACK) && isGetUserInput) {
            SectionView.showSelectGuideMessage();
            input = user.userInput(scanner);

            isGetUserInput = Checker.checkUserInputIsNotValid(input, SECTION_OPTION_START, SECTION_OPTION_END, false);
            isGetUserInput = whichOptionInSectionManage(input, isGetUserInput, scanner, user);
        }
    }

    public boolean whichOptionInSectionManage(String input, boolean isGetUserInput, Scanner scanner, User user) {
        if (input.equals(OPTION_ONE)) {
            isGetUserInput = isSectionOptionOneError(scanner, user);
        }
        if (input.equals(OPTION_TWO)) {
            isGetUserInput = isSectionOptionTwoError(scanner, user);
        }

        return isGetUserInput;
    }

    public void lineManage(Scanner scanner, User user) {
        boolean isGetUserInput = true;
        String input = INITIAL_STRING_VARIABLE;

        while (!input.equals(GO_BACK) && isGetUserInput) {
            LineView.showSelectGuideMessage();
            input = user.userInput(scanner);

            isGetUserInput = Checker.checkUserInputIsNotValid(input, LINE_OPTION_START, LINE_OPTION_END, false);
            isGetUserInput = whichOptionInLineManage(input, isGetUserInput, user, scanner);
        }
    }

    public boolean whichOptionInLineManage(String input, boolean isGetUserInput, User user, Scanner scanner) {
        if (input.equals(OPTION_ONE)) {
            isGetUserInput = isLineOptionOneError(user, scanner);
        }
        if (input.equals(OPTION_TWO)) {
            isGetUserInput = isLineOptionTwoError(user, scanner);
        }
        if (input.equals(OPTION_THREE)) {
            isGetUserInput = LineView.showLineAllMessage();
        }
        return isGetUserInput;
    }

    public boolean isLineOptionOneError(User user, Scanner scanner) {
        LineView.showLineInsertNameGuide();
        String userLineNameInput = user.userInput(scanner);

        if (!Checker.isLengthOverTwo(userLineNameInput) || Checker.isSameLine(userLineNameInput)) {
            LineView.showLineMenuGuide();
            return true;
        }
        addLineInLineRepository(userLineNameInput, user, scanner);

        return false;
    }

    public void addLineInLineRepository(String userLineNameInput, User user, Scanner scanner) {
        LineRepository.addLine(new Line(userLineNameInput));

        LineView.showInsertStartStationInLineGuide();
        String startStation = user.userInput(scanner);
        LineView.showInsertEndStationInLineGuide();
        String endStation = user.userInput(scanner);

        LineRepository.addStationsInLine(LineRepository.lines().size() - LAST_INDEX_CONSTANT, startStation, endStation);
        LineView.showLineInsertComplete();
    }

    public boolean isLineOptionTwoError(User user, Scanner scanner) {
        LineView.showLineRemoveGuide();
        String userRemoveLineInput = user.userInput(scanner);
        removeLineInLineRepository(userRemoveLineInput);

        return false;
    }

    public void removeLineInLineRepository(String userRemoveLineInput) {
        LineRepository.deleteLineByName(userRemoveLineInput);
        LineView.showLineRemoveComplete();
    }

    public void stationManage(Scanner scanner, User user) {
        boolean isGetUserInput = true;
        String input = INITIAL_STRING_VARIABLE;

        while (!input.equals(GO_BACK) && isGetUserInput) {
            StationView.showSelectGuideMessage();
            input = scanner.nextLine();

            isGetUserInput = Checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END, false);
            isGetUserInput = whichOptionInStationManage(input, isGetUserInput, user, scanner);
        }
    }

    public boolean whichOptionInStationManage(String input, boolean isGetUserInput, User user, Scanner scanner) {
        if (input.equals(OPTION_ONE)) {
            isGetUserInput = isStationOptionOneError(user, scanner);
        }
        if (input.equals(OPTION_TWO)) {
            isGetUserInput = isStationOptionTwoError(user, scanner);
        }
        if (input.equals(OPTION_THREE)) {
            isGetUserInput = StationView.showStationAllMessage();
        }
        return isGetUserInput;
    }

    public boolean isStationOptionOneError(User user, Scanner scanner) {
        StationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);

        if (Checker.isLengthOverTwo(userStationNameInput)) {
            if (!Checker.isSameName(userStationNameInput)) {
                addStationInStationRepository(userStationNameInput);
                return false;
            }
        }
        return true;
    }

    public void addStationInStationRepository(String userStationNameInput) {
        StationRepository.addStation(new Station(userStationNameInput));
        StationView.showStationInsertComplete();
    }

    public boolean isStationOptionTwoError(User user, Scanner scanner) {
        StationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);
        if (!Checker.isContainStationInLine(userStationInput)) {
            deleteStationInStationRepository(userStationInput);
            return false;
        }
        return true;
    }

    public void deleteStationInStationRepository(String userStationInput) {
        StationRepository.deleteStation(userStationInput);
        StationView.showStationRemoveComplete();
    }
}
