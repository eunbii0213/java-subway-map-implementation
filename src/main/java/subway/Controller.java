package subway;

import subway.Input.MenuInput;
import subway.Input.UserInput;
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

    public void startSubwayMap(String userInput, Scanner scanner) {
        if (userInput.equals(START_STATION_MANAGEMENT)) {
            StationView.showStationMenuGuide();
            stationManage(scanner,INITIALIZE_STRING_VARIABLE);
        }
        if (userInput.equals(START_LINE_MANAGEMENT)) {
            LineView.showLineMenuGuide();
            lineManage(scanner, INITIALIZE_STRING_VARIABLE);
        }
        if (userInput.equals(START_SECTION_MANAGEMENT)) {
            SectionView.showSectionMenuGuide();
            sectionManage(scanner, INITIALIZE_STRING_VARIABLE);
        }
        if (userInput.equals(PRINT_ALL_LINES_AND_STATIONS)) {
            View.printAllLineAndStationInfo();
        }
    }

    public void sectionAddOption(Scanner scanner) {
        try {
            addSectionInLine(scanner);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addSectionInLine(Scanner scanner) {
        String userInputLineName = new UserInput().userInput(scanner);
        try {
            int lineIndex = LineRepository.findLineIndexFromLines(userInputLineName);
            Line line = LineRepository.getLines(lineIndex);

            SectionView.showSectorInsertStationNameGuide();
            String userInputStationName = new UserInput().userInput(scanner);
            line.checkStationExistInSubwayMap(userInputStationName);

            SectionView.showSectorInsertNumberGuide();
            line.addStationInLine(Integer.parseInt(new UserInput().userInput(scanner)), userInputStationName);

            SectionView.showSectorInsertComplete();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void sectionRemoveOption(Scanner scanner) {
        try {
            int lineIndex = LineRepository.findLineIndexFromLines(new UserInput().userInput(scanner));
            Line line = LineRepository.getLines(lineIndex);

            SectionView.showSectorRemoveStationGuide();
            String stationName = new UserInput().userInput(scanner);
            removeStationInLine(line, stationName);
        } catch (Exception e) {
            View.showExceptionMessage(e);
        }
    }

    public void removeStationInLine(Line line, String stationName) {
        try {
            line.removeStation(stationName);
            SectionView.showSectorRemoveComplete();
        } catch (Exception e) {
            View.showExceptionMessage(e);
        }
    }

    public void sectionManage(Scanner scanner, String userInput) {
        while (!userInput.equals(GO_BACK)) {
            SectionView.showSelectGuideMessage();
            try {
                MenuInput input = new MenuInput(scanner, SECTION_OPTION_START, SECTION_OPTION_END, false);
                userInput = input.getUserInput();
                try {
                    selectOptionInSectionManage(userInput, scanner);
                    break;
                } catch (Exception e) {
                    SectionView.sectionManageOptionInputErrorView(e);
                }
            } catch (Exception e) {
                View.showExceptionMessage(e);
            }
        }
    }

    public void selectOptionInSectionManage(String userInput, Scanner scanner) {
        try {
            if (userInput.equals(SECTION_ADD)) {
                SectionView.showSectionInsertGuide();
                sectionAddOption(scanner);
            }
            if (userInput.equals(SECTION_REMOVE)) {
                SectionView.showSectorRemoveLineGuide();
                sectionRemoveOption(scanner);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void lineManage(Scanner scanner, String userInput) {
        while (!userInput.equals(GO_BACK)) {
            try {
                LineView.showSelectGuideMessage();
                MenuInput input = new MenuInput(scanner, LINE_OPTION_START, LINE_OPTION_END, false);
                userInput = input.getUserInput();
                try {
                    selectOptionInLineManage(scanner, userInput);
                    break;
                } catch (Exception e) {
                    LineView.lineManageOptionInputErrorView(e);
                    //continue;
                }
            } catch (Exception e) {
                View.showExceptionMessage(e);
            }
        }
    }

    public void selectOptionInLineManage(Scanner scanner, String userInput) {
        UserInput input = new UserInput();
        try {
            if (userInput.equals(LINE_ADD)) {
                lineAddOption(scanner);
            }
            if (userInput.equals(LINE_REMOVE)) {
                lineRemoveOption(scanner, input);
            }
            if (userInput.equals(PRINT_ALL_LINES)) {
                LineView.showLineAllMessage();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void lineAddOption(Scanner scanner) {
        LineView.showLineInsertNameGuide();
        UserInput input = new UserInput();
        String userLineNameInput = input.userInput(scanner);
        LineView.showInsertStartStationInLineGuide();

        try {
            addLineInLineRepository(userLineNameInput, input, scanner);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addLineInLineRepository(String userLineNameInput, UserInput input, Scanner scanner) {
        String startStationName = input.userInput(scanner);

        try {
            Station startStation = StationRepository.getStation(StationRepository.findStationIndex(startStationName));

            LineView.showInsertEndStationInLineGuide();
            String endStationName = input.userInput(scanner);
            Station endStation = StationRepository.getStation(StationRepository.findStationIndex(endStationName));
            LineRepository.addLine(Line.createLineEntity(userLineNameInput, startStation, endStation));

            try {
                LineView.showLineInsertComplete();
            } catch (Exception e) {
                LineRepository.removeLastLine();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void lineRemoveOption(Scanner scanner, UserInput input) {
        LineView.showLineRemoveGuide();
        String userRemoveLineInput = input.userInput(scanner);
        try {
            removeLineInLineRepository(userRemoveLineInput);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void removeLineInLineRepository(String userRemoveLineInput) {
        try {
            LineRepository.deleteLineByName(userRemoveLineInput);
            LineView.showLineRemoveComplete();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void stationManage(Scanner scanner,String userInput) {
        while (!userInput.equals(GO_BACK)) {
            StationView.showSelectGuideMessage();

            try {
                MenuInput menuInput = new MenuInput(scanner, STATION_OPTION_START, STATION_OPTION_END, false);
                try {
                    userInput = menuInput.getUserInput();
                    selectOptionInStationManage(userInput, scanner);
                    break;
                } catch (Exception e) {
                    StationView.stationManageOptionInputErrorView(e);
                }
            } catch (Exception e) {
                View.showExceptionMessage(e);
            }
        }
    }

    public void selectOptionInStationManage(String userInput, Scanner scanner) {
        try {
            if (userInput.equals(STATION_ADD)) {
                stationAddOption(scanner);
            }
            if (userInput.equals(STATION_REMOVE)) {
                stationRemoveOption(scanner);
            }
            if (userInput.equals(PRINT_ALL_STATIONS)) {
                StationView.showStationAllMessage();
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void stationAddOption(Scanner scanner) {
        StationView.showStationInsertGuide();

        try {
            String userStationNameInput = new UserInput().userInput(scanner);
            addStationInStationRepository(userStationNameInput);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void addStationInStationRepository(String userStationNameInput) {
        try {
            StationRepository.addStation(Station.createStationEntity(userStationNameInput));
            StationView.showStationInsertComplete();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void stationRemoveOption(Scanner scanner) {
        StationView.showStationRemoveGuide();
        String userStationInput = new UserInput().userInput(scanner);
        try {
            deleteStationInStationRepository(userStationInput);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void deleteStationInStationRepository(String userStationInput) {
        try {
            StationRepository.deleteStation(userStationInput);
            StationView.showStationRemoveComplete();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
