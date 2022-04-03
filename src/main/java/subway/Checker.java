package subway;

import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.view.ErrorView;

import java.util.List;

public class Checker {
    private static final String QUIT = "Q";
    private static final String GO_BACK = "B";
    private static final int MINIMUM_LENGTH = 2;
    private static final int SEARCH_ERROR = -1;

    public static boolean isUserNumberInputError(int userInputIndex, int subwayMapSize) {
        if (userInputIndex > subwayMapSize) {
            ErrorView.userNumberInputError();
            return true;
        }
        return false;
    }

    public static boolean isLineOrStationInputError(int searchResult, boolean isForLine) {
        if (searchResult == SEARCH_ERROR) {
            if (isForLine) {
                ErrorView.findLineError();
                return true;
            }
            ErrorView.findStationError();
            return true;
        }
        return false;
    }

    public static boolean isContainStationInLine(String userStationInput) {
        for (Line line : LineRepository.getLines()) {
            for (Station station : line.getSubwayMap()) {
                if (station.getName().equals(userStationInput)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isContainStationInOneLine(String userStationInput, List<Station> subwayMap) {
        for (Station station : subwayMap) {
            if (station.getName().equals(userStationInput)) {
                return true;
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

    public static boolean isLineSizeOverTwo(Line line) {
        if (line.getSubwayMapSize() <= MINIMUM_LENGTH) {
            ErrorView.getErrorView();
            return false;
        }
        return true;
    }

    public static boolean isSameName(String userInput) {

        for (Station station : StationRepository.getStations()) {
            if (station.getName().equals(userInput)) {
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
        for (Line line : LineRepository.getLines()) {
            if (line.getName().equals(userInput)) {
                ErrorView.addSameLineError();
                return true;
            }
        }
        return false;
    }
}
