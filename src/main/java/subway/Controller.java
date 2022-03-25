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

    public void startSubwayMap(StationView stationView, LineView lineView, View view, SectionView sectionView, Section section, String input, Checker checker, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        if (input.equals(OPTION_ONE)) {
            stationView.showStationMenuGuide();
            stationManage(true,INITIAL_STRING_VARIABLE,checker, stationView, scanner, user, lineRepository, stationRepository);
        }
        if (input.equals(OPTION_TWO)) {
            lineView.showLineMenuGuide();
            lineManage(true, INITIAL_STRING_VARIABLE, checker, lineView, scanner, user, lineRepository, stationRepository);
        }
        if (input.equals(OPTION_THREE)) {
            sectionView.showSectorMenuGuide();
            sectionManage(true, INITIAL_STRING_VARIABLE, checker, sectionView, section, scanner, user, lineRepository, stationRepository);
        }
        if (input.equals(OPTION_FOUR)) {
            view.printAllLineAndStationInfo(lineRepository);
        }
    }

    public boolean isSectionOptionOneError(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository, Section section, SectionView sectionView) {
        sectionView.showSectorInsertGuide();
        String userInputLineName = user.userInput(scanner);
        Line line = section.findLineIndexFromLines(lineRepository, userInputLineName);

        sectionView.showSectorInsertStationNameGuide();
        String userInputStationName = user.userInput(scanner);
        int stationIndex = section.findStationIndexFromStations(stationRepository, userInputStationName);
        Station station = stationRepository.getStations().get(stationIndex);

        sectionView.showSectorInsertNumberGuide();
        String userInputIndex = user.userInput(scanner);

        int index = Integer.parseInt(userInputIndex) - 1;
        section.addStationInLines(index, line, station);

        sectionView.showSectorInsertComplete();
        return false;
    }

    public boolean isSectionOptionTwoError(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository, Section section, SectionView sectionView, Checker checker) {
        sectionView.showSectorRemoveLineGuide();
        Line line = section.findLineIndexFromLines(lineRepository, user.userInput(scanner));

        sectionView.showSectorRemoveStationGuide();
        int stationIndex = section.findStationIndexFromStations(stationRepository, user.userInput(scanner));
        Station station = stationRepository.getStations().get(stationIndex);

        int index = line.searchTargetLineIndexInSubwayMap(station.getName()) - 1;

        if (sectionCheck(checker, line, sectionView, index, stationIndex, stationRepository)) {
            return true;
        }

        line.removeStation(index);
        sectionView.showSectorRemoveComplete();
        return false;
    }

    public boolean sectionCheck(Checker checker, Line line, SectionView sectionView, int index, int stationIndex, StationRepository stationRepository) {
        if (!checker.isLineSizeOverTwo(line)) {
            sectionView.showSelectGuideMessage();
            return true;
        }
        if (checker.isLastStation(line, index)) {
            line.changeEndStationAfterRemoveEndStation(index, stationIndex, stationRepository);
        }
        return false;
    }

    public void sectionManage(boolean getUserInputAgain, String input, Checker checker, SectionView sectionView, Section section, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        while (!input.equals(GO_BACK) && getUserInputAgain) {
            input = user.userInput(scanner);
            sectionView.showSelectGuideMessage();

            if (checker.checkUserInputIsNotValid(input, SECTION_OPTION_START, SECTION_OPTION_END)) {
                sectionView.showSelectGuideMessage();
            }
            if (input.equals(OPTION_ONE)) {
                getUserInputAgain = isSectionOptionOneError(scanner, user, lineRepository, stationRepository, section, sectionView);
            }
            if (input.equals(OPTION_TWO)) {
                getUserInputAgain = isSectionOptionTwoError(scanner, user, lineRepository, stationRepository, section, sectionView, checker);
            }
        }
    }

    public void lineManage(boolean getUserInputAgain, String input, Checker checker, LineView lineView, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        while (!input.equals(GO_BACK) && getUserInputAgain) {
            lineView.showSelectGuideMessage();
            input = user.userInput(scanner);
            getUserInputAgain = checker.checkUserInputIsNotValid(input, LINE_OPTION_START, LINE_OPTION_END);

            if (input.equals(OPTION_ONE)) {
                getUserInputAgain = isLineOptionOneError(lineView, checker, user, scanner, lineRepository, stationRepository);
            }
            if (input.equals(OPTION_TWO)) {
                getUserInputAgain = isLineOptionTwoError(lineView, user, scanner, lineRepository);
            }
            if (input.equals(OPTION_THREE)) {
                getUserInputAgain = lineView.showLineAllMessage(lineRepository);
            }
        }
    }

    public boolean isLineOptionOneError(LineView lineView, Checker checker, User user, Scanner scanner, LineRepository lineRepository, StationRepository stationRepository) {
        lineView.showLineInsertNameGuide();
        String userLineNameInput = user.userInput(scanner);

        if (!checker.isLengthOverTwo(userLineNameInput) || checker.isSameLine(userLineNameInput, lineRepository)) {
            lineView.showLineMenuGuide();
            return true;
        }

        lineRepository.addLine(new Line(userLineNameInput));

        lineView.showInsertStartStationInLineGuide();
        String startStation = user.userInput(scanner);
        lineView.showInsertEndStationInLineGuide();
        String endStation = user.userInput(scanner);

        lineRepository.getListLines().get(lineRepository.lines().size() - 1).addStationsInLine(startStation, endStation, stationRepository);

        lineView.showLineInsertComplete();
        return false;
    }

    public boolean isLineOptionTwoError(LineView lineView, User user, Scanner scanner, LineRepository lineRepository) {
        lineView.showLineRemoveGuide();
        String userRemoveLineInput = user.userInput(scanner);
        lineRepository.deleteLineByName(userRemoveLineInput);
        lineView.showLineRemoveComplete();

        return false;
    }

    public void stationManage(boolean getUserInputAgain, String input, Checker checker, StationView stationView, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        while (!input.equals(GO_BACK) && getUserInputAgain) {
            stationView.showSelectGuideMessage();
            input = scanner.nextLine();

            getUserInputAgain = checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END);

            if (input.equals(OPTION_ONE)) {
                getUserInputAgain = isStationOptionOneError(stationView, checker, user, scanner, stationRepository);
            }
            if (input.equals(OPTION_TWO)) {
                getUserInputAgain = isStationOptionTwoError(stationView, checker, user, scanner, stationRepository, lineRepository);
            }
            if (input.equals(OPTION_THREE)) {
                getUserInputAgain = stationView.showStationAllMessage(stationRepository);
            }
        }
    }

    public boolean isStationOptionOneError(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository) {
        stationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);

        if (checker.isLengthOverTwo(userStationNameInput)) {
            if (!checker.isSameName(userStationNameInput, stationRepository)) {
                stationRepository.addStation(new Station(userStationNameInput));
                stationView.showStationInsertComplete();
                return false;
            }
        }
        return true;
    }

    public boolean isStationOptionTwoError(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository, LineRepository lineRepository) {
        stationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);
        if (!checker.isContainStationInLine(lineRepository, userStationInput)) {
            stationRepository.deleteStation(userStationInput);
            stationView.showStationRemoveComplete();
            return false;
        }
        return true;
    }
}
