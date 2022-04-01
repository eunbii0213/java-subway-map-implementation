package subway;

import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.domain.*;
import subway.view.LineView;
import subway.view.SectionView;
import subway.view.StationView;
import subway.view.View;

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
        Station station = StationRepository.getStationFromStations(stationIndex);

        SectionView.showSectorInsertNumberGuide();
        String userInputIndex = user.userInput(scanner);

        int index = (Integer.parseInt(userInputIndex));
        line.addStationInLine(--index, station);

        SectionView.showSectorInsertComplete();
    }

    public boolean isSectionOptionTwoError(Scanner scanner, User user) {
        SectionView.showSectorRemoveLineGuide();
        Line line = Section.findLineIndexFromLines(user.userInput(scanner));

        SectionView.showSectorRemoveStationGuide();
        int stationIndex = Section.findStationIndexFromStations(user.userInput(scanner));

        Station station = StationRepository.getStationFromStations(stationIndex);
        int index = line.searchTargetStationIndexInSubwayMap(station.getName());

        if (removeStationInLine(line, index)) {
            return true;
        }
        return false;
    }

    public boolean removeStationInLine(Line line, int index) {
        if (line.removeStation(index, line)) {
            return true;
        }

        SectionView.showSectorRemoveComplete();
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

        if (addLineInLineRepository(userLineNameInput, user, scanner)) {
            LineView.showLineMenuGuide();
            return true;
        }
        return false;
    }

    public boolean addLineInLineRepository(String userLineNameInput, User user, Scanner scanner) {
        if (LineRepository.addLine(new Line(userLineNameInput), userLineNameInput)) {
            return true;
        }

        LineView.showInsertStartStationInLineGuide();
        String startStation = user.userInput(scanner);
        LineView.showInsertEndStationInLineGuide();
        String endStation = user.userInput(scanner);

        LineRepository.addStationsInLine(startStation, endStation);
        LineView.showLineInsertComplete();
        return false;
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

        if (addStationInStationRepository(userStationNameInput)) {
            return true;
        }
        return false;
    }

    public boolean addStationInStationRepository(String userStationNameInput) {
        if (StationRepository.addStation(new Station(userStationNameInput), userStationNameInput)) {
            return true;
        }
        StationView.showStationInsertComplete();
        return false;
    }

    public boolean isStationOptionTwoError(User user, Scanner scanner) {
        StationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);

        if (deleteStationInStationRepository(userStationInput)) {
            return true;
        }
        return false;
    }

    public boolean deleteStationInStationRepository(String userStationInput) {
        if (StationRepository.deleteStation(userStationInput)) {
            return true;
        }
        StationView.showStationRemoveComplete();
        return false;
    }
}
