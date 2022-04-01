package subway;

import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.domain.Line;
import subway.view.ErrorView;

public class Checker {
    private static final String QUIT = "Q";
    private static final String GO_BACK = "B";
    private static final int INITIAL_INDEX = 0;
    private static final int MINIMUM_LENGTH = 2;

    public static boolean isContainStationInLine(String userStationInput) {
        for (int index = INITIAL_INDEX; index < LineRepository.getLinesSize(); index++) {
            Line nowLine = LineRepository.getLines(index);
            for (int searchIndex = INITIAL_INDEX; searchIndex < nowLine.getSubwayMapSize(); searchIndex++) {
                if (nowLine.getStationFromSubwayMap(searchIndex).getName().equals(userStationInput)) {
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

    public static boolean isLineSizeOverTwo(Line line) {
        if (line.getSubwayMapSize() <= MINIMUM_LENGTH) {
            ErrorView.getErrorView();
            return false;
        }
        return true;
    }

    public static boolean isSameName(String userInput) {
        for (int index = INITIAL_INDEX; index < StationRepository.getStationSize(); index++) {
            if (StationRepository.getStationFromStations(index).getName().equals(userInput)) {
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
        for (int index = INITIAL_INDEX; index < LineRepository.getLinesSize(); index++) {
            if (LineRepository.getLines(index).getName().equals(userInput)) {
                ErrorView.addSameLineError();
                return true;
            }
        }
        return false;
    }
}
