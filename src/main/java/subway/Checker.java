package subway;

import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.StationRepository;

public class Checker {
    private static final String GO_BACK = "B";
    ErrorView errorView = new ErrorView();
    private static final int INITIAL_INDEX = 0;
    private static final int MINIMUM_LENGTH=2;

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
        if (userInput.length() < MINIMUM_LENGTH) {
            errorView.userInputLengthError();
            return false;
        }
        return true;
    }

    public boolean isLastStation(Line line,int index){
        if(line.getSubwayMap().size()-1 == index){
            return true;
        }
        return false;
    }

    public boolean isLineSizeOverTwo(Line line){
        if(line.getSubwayMap().size()<=MINIMUM_LENGTH){
            errorView.getErrorView();
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
            if ((userInputToInt < optionStart || userInputToInt > optionEnd)) {
                errorView.userInputError();
                return true;
            }
        } catch (NumberFormatException e) {
            if(!userInput.equals(GO_BACK)){
                errorView.userInputError();
                return true;
            }
        }
        return false;
    }

    public boolean isSameLine(String userInput,LineRepository lineRepository){
        for(int index = INITIAL_INDEX;index<lineRepository.lines().size();index++) {
            if (lineRepository.lines().get(index).getName().equals(userInput)){
                errorView.addSameLineError();
                return true;
            }
        }
        return false;
    }
}
