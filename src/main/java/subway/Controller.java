package subway;

import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.view.LineView;
import subway.view.SectionView;
import subway.view.StationView;
import subway.view.View;

import java.util.Scanner;

public class Controller {
    private static final String START_STATION_MANAGEMENT = "1";
    private static final String START_LINE_MANAGEMENT = "2";
    private static final String START_SECTION_MANAGEMENT = "3";
    private static final String PRINT_ALL_LINES_AND_STATIONS = "4";
    private static final String STATION_ADD = "1";
    private static final String STATION_REMOVE = "2";
    private static final String PRINT_ALL_STATIONS = "3";
    private static final String LINE_ADD = "1";
    private static final String LINE_REMOVE = "2";
    private static final String PRINT_ALL_LINES = "3";
    private static final String SECTION_ADD = "1";
    private static final String SECTION_REMOVE = "2";
    private static final String GO_BACK = "B";
    private static final String INITIALIZE_STRING_VARIABLE = "";
    private static final int STATION_OPTION_START = 1;
    private static final int STATION_OPTION_END = 3;
    private static final int LINE_OPTION_START = 1;
    private static final int LINE_OPTION_END = 3;
    private static final int SECTION_OPTION_START = 1;
    private static final int SECTION_OPTION_END = 2;
    private static final int LAST_INDEX = 1;

    public void startSubwayMap(String input, Scanner scanner, User user) {
        if (input.equals(START_STATION_MANAGEMENT)) {
            StationView.showStationMenuGuide();
            stationManage(scanner, user);
        }
        if (input.equals(START_LINE_MANAGEMENT)) {
            LineView.showLineMenuGuide();
            lineManage(scanner, user);
        }
        if (input.equals(START_SECTION_MANAGEMENT)) {
            SectionView.showSectorMenuGuide();
            sectionManage(scanner, user);
        }
        if (input.equals(PRINT_ALL_LINES_AND_STATIONS)) {
            View.printAllLineAndStationInfo();
        }
    }

    public boolean isSectionAddError(Scanner scanner, User user) {
        if (addSectionInLines(scanner, user)) {
            return true;
        }
        return false;
    }

    public boolean addSectionInLines(Scanner scanner, User user) {
        SectionView.showSectorInsertGuide();
        String userInputLineName = user.userInput(scanner);
        int lineIndex = LineRepository.findLineIndexFromLines(userInputLineName);
        boolean isError = Checker.isLineOrStationInputError(lineIndex, true);

        if (!isError) {
            Line line = LineRepository.getLines(lineIndex);
            SectionView.showSectorInsertStationNameGuide();
            String userInputStationName = user.userInput(scanner);
            SectionView.showSectorInsertNumberGuide();
            isError = line.addStationInLine(Integer.parseInt(user.userInput(scanner)) - LAST_INDEX, userInputStationName);

            if (!isError) {
                SectionView.showSectorInsertComplete();
            }
        }
        return isError;
    }

    public boolean isSectionRemoveError(Scanner scanner, User user) {
        SectionView.showSectorRemoveLineGuide();
        int lineIndex = LineRepository.findLineIndexFromLines(user.userInput(scanner));
        boolean isError = Checker.isLineOrStationInputError(lineIndex, true);
        if (!isError) {
            Line line = LineRepository.getLines(lineIndex);
            SectionView.showSectorRemoveStationGuide();
            String stationName = user.userInput(scanner);
            isError = removeStationInLine(line, stationName);
        }
        return isError;
    }

    public boolean removeStationInLine(Line line, String stationName) {
        if (line.removeStation(line, stationName)) {
            return true;
        }
        SectionView.showSectorRemoveComplete();
        return false;
    }

    public void sectionManage(Scanner scanner, User user) {
        boolean isGetUserInput = true;
        String input = INITIALIZE_STRING_VARIABLE;

        while (!input.equals(GO_BACK) && isGetUserInput) {
            SectionView.showSelectGuideMessage();
            input = user.userInput(scanner);

            isGetUserInput = Checker.checkUserInputIsNotValid(input, SECTION_OPTION_START, SECTION_OPTION_END, false);
            isGetUserInput = whichOptionInSectionManage(input, isGetUserInput, scanner, user);
        }
    }

    public boolean whichOptionInSectionManage(String input, boolean isGetUserInput, Scanner scanner, User user) {
        if (input.equals(SECTION_ADD)) {
            isGetUserInput = isSectionAddError(scanner, user);
        }
        if (input.equals(SECTION_REMOVE)) {
            isGetUserInput = isSectionRemoveError(scanner, user);
        }
        if (isGetUserInput) {
            SectionView.showSectorMenuGuide();
        }
        return isGetUserInput;
    }

    public void lineManage(Scanner scanner, User user) {
        boolean isGetUserInput = true;
        String input = INITIALIZE_STRING_VARIABLE;

        while (!input.equals(GO_BACK) && isGetUserInput) {
            LineView.showSelectGuideMessage();
            input = user.userInput(scanner);

            isGetUserInput = Checker.checkUserInputIsNotValid(input, LINE_OPTION_START, LINE_OPTION_END, false);
            isGetUserInput = whichOptionInLineManage(input, isGetUserInput, user, scanner);
        }
    }

    public boolean whichOptionInLineManage(String input, boolean isGetUserInput, User user, Scanner scanner) {
        if (input.equals(LINE_ADD)) {
            isGetUserInput = isLineAddError(user, scanner);
        }
        if (input.equals(LINE_REMOVE)) {
            isGetUserInput = isLineRemoveError(user, scanner);
        }
        if (input.equals(PRINT_ALL_LINES)) {
            isGetUserInput = LineView.showLineAllMessage();
        }
        if (isGetUserInput) {
            LineView.showLineMenuGuide();
        }
        return isGetUserInput;
    }

    public boolean isLineAddError(User user, Scanner scanner) {
        LineView.showLineInsertNameGuide();
        String userLineNameInput = user.userInput(scanner);

        if (addLineInLineRepository(userLineNameInput, user, scanner)) {
            LineRepository.removeLastLine();
            return true;
        }
        return false;
    }

    //20line.
    public boolean addLineInLineRepository(String userLineNameInput, User user, Scanner scanner) {
        if (LineRepository.addLine(new Line(userLineNameInput))) {
            return true;
        }
        LineView.showInsertStartStationInLineGuide();
        String startStation = user.userInput(scanner);

        LineView.showInsertEndStationInLineGuide();
        String endStation = user.userInput(scanner);

        Line line = LineRepository.getLastLine();
        boolean isError = line.addStationsInLine(startStation, endStation);

        if (!isError) {
            LineView.showLineInsertComplete();
        }
        return isError;
    }

    public boolean isLineRemoveError(User user, Scanner scanner) {
        LineView.showLineRemoveGuide();
        String userRemoveLineInput = user.userInput(scanner);
        boolean isError = removeLineInLineRepository(userRemoveLineInput);
        return isError;
    }

    public boolean removeLineInLineRepository(String userRemoveLineInput) {
        boolean isError = LineRepository.deleteLineByName(userRemoveLineInput);
        if (!isError) {
            LineView.showLineRemoveComplete();
        }
        return isError;
    }

    public void stationManage(Scanner scanner, User user) {
        boolean isGetUserInput = true;
        String input = INITIALIZE_STRING_VARIABLE;

        while (!input.equals(GO_BACK) && isGetUserInput) {
            StationView.showSelectGuideMessage();
            input = scanner.nextLine();

            isGetUserInput = Checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END, false);
            isGetUserInput = whichOptionInStationManage(input, isGetUserInput, user, scanner);
        }
    }

    public boolean whichOptionInStationManage(String input, boolean isGetUserInput, User user, Scanner scanner) {
        if (input.equals(STATION_ADD)) {
            isGetUserInput = isStationAddError(user, scanner);
        }
        if (input.equals(STATION_REMOVE)) {
            isGetUserInput = isStationRemoveError(user, scanner);
        }
        if (input.equals(PRINT_ALL_STATIONS)) {
            isGetUserInput = StationView.showStationAllMessage();
        }
        if (isGetUserInput) {
            StationView.showStationMenuGuide();
        }
        return isGetUserInput;
    }

    public boolean isStationAddError(User user, Scanner scanner) {
        StationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);

        if (addStationInStationRepository(userStationNameInput)) {
            return true;
        }
        return false;
    }

    public static boolean addStationInStationRepository(String userStationNameInput) {
        if (StationRepository.addStation(new Station(userStationNameInput))) {
            return true;
        }
        StationView.showStationInsertComplete();
        return false;
    }

    public boolean isStationRemoveError(User user, Scanner scanner) {
        StationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);
        boolean isError = deleteStationInStationRepository(userStationInput);
        return isError;
    }

    public boolean deleteStationInStationRepository(String userStationInput) {
        if (StationRepository.deleteStation(userStationInput)) {
            return true;
        }
        StationView.showStationRemoveComplete();
        return false;
    }
}
