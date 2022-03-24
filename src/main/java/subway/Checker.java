package subway;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.StationRepository;

public class Checker {
    ErrorView errorView = new ErrorView();
    private static final int INITIAL_INDEX = 0;
    private static final int MINIMUM_STATION_LENGTH=2;

    public void stationAddUserInputChecker(String userInput, StationRepository stationRepository) {
        boolean check = false;
        while (!check) {
            check = isLengthOverTwo(userInput);
            if(check){
                continue;
            }
            check = isSameName(userInput, stationRepository);
        }
    }

    public boolean isContainStationInLine(LineRepository lineRepository, String userStationInput) {
        for (int index = INITIAL_INDEX; index < lineRepository.lines().size(); index++) {
            Line nowLine = lineRepository.getLines(index);
            for (int searchIndex = INITIAL_INDEX; searchIndex < nowLine.getSubwayMap().size(); searchIndex++) {
                if (nowLine.getSubwayMap().get(searchIndex).getName().equals(userStationInput)) {
                    errorView.removeErrorStationInLine();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLengthOverTwo(String userInput) {
        if (userInput.length() < MINIMUM_STATION_LENGTH) {
            errorView.userInputLengthError();
            return false;
        }
        return true;
    }

    public boolean isSameName(String userInput, StationRepository stationRepository) {
        for (int index = INITIAL_INDEX; index < stationRepository.stations().size(); index++) {
            if (stationRepository.stations().get(index).getName().equals(userInput)) {
                errorView.addSameStationError();
                return true;
            }
        }
        return false;
    }

    public boolean checkUserInputIsNotValid(String userInput, int optionStart, int optionEnd) {
        int userInputToInt;
        try {
            userInputToInt = Integer.parseInt(userInput);
            if (userInputToInt < optionStart || userInputToInt > optionEnd) {
                errorView.userInputError();
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
}
