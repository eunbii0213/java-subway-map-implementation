package subway;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.StationRepository;

public class Checker {
    private static final String QUIT = "Q";
    private static final String GO_BACK = "B";
    private static final int INITIAL_INDEX = 0;
    private static final int MINIMUM_LENGTH = 2;
    private static final int LAST_INDEX_CONSTANT = 1;

    public static boolean isContainStationInLine(String userStationInput) {
        for (int index = INITIAL_INDEX; index < LineRepository.lines().size(); index++) {
            Line nowLine = LineRepository.getLines(index);
            for (int searchIndex = INITIAL_INDEX; searchIndex < nowLine.getSubwayMap().size(); searchIndex++) {
                if (nowLine.getSubwayMap().get(searchIndex).getName().equals(userStationInput)) {
                    ErrorView.removeErrorStationInLine();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLengthOverTwo(String userInput) {
        if (userInput.length() < MINIMUM_LENGTH) {
            ErrorView.userInputLengthError();
            return false;
        }
        return true;
    }

    public static boolean isLastStation(Line line, int index, int stationIndex) {
        if (line.getSubwayMap().size() - LAST_INDEX_CONSTANT == index && stationIndex < StationRepository.getStations().size() - LAST_INDEX_CONSTANT) {
            return true;
        }
        return false;
    }

    public static boolean isLineSizeOverTwo(Line line) {
        if (line.getSubwayMap().size() <= MINIMUM_LENGTH) {
            ErrorView.getErrorView();
            return false;
        }
        return true;
    }

    public static boolean isSameName(String userInput) {
        for (int index = INITIAL_INDEX; index < StationRepository.stations().size(); index++) {
            if (StationRepository.stations().get(index).getName().equals(userInput)) {
                ErrorView.addSameStationError();
                return true;
            }
        }
        return false;
    }

    public static boolean checkUserInputIsNotValid(String userInput, int optionStart, int optionEnd, boolean isMainMenu) {
        int userInputToInt;
        try {
            userInputToInt = Integer.parseInt(userInput);
            if ((userInputToInt < optionStart || userInputToInt > optionEnd)) {
                ErrorView.userInputError();
                return true;
            }
        } catch (NumberFormatException e) {
            if (isMainMenu && userInput.equals(QUIT)) {
                return false;
            }
            if (isMainMenu && userInput.equals(GO_BACK)) {
                ErrorView.userInputError();
                return true;
            }
            if (!userInput.equals(GO_BACK)) {
                ErrorView.userInputError();
                return true;
            }
        }
        return false;
    }

    public static boolean isSameLine(String userInput) {
        for (int index = INITIAL_INDEX; index < LineRepository.lines().size(); index++) {
            if (LineRepository.lines().get(index).getName().equals(userInput)) {
                ErrorView.addSameLineError();
                return true;
            }
        }
        return false;
    }
}
