package subway.domain;

public class Section {
    private static final int FIND_ERROR = -1;
    private static final int INITIAL_INDEX = 0;

    public static Line findLineIndexFromLines(String userInput) {
        for (int index = INITIAL_INDEX; index < LineRepository.getLinesSize(); index++) {
            if (LineRepository.getLines(index).getName().equals(userInput)) {
                return LineRepository.getLines(index);
            }
        }
        return LineRepository.getLines(FIND_ERROR);
    }

    public static int findStationIndexFromStations(String userInput) {
        for (int index = INITIAL_INDEX; index < StationRepository.getStationSize(); index++) {
            if (StationRepository.getStationFromStations(index).getName().equals(userInput)) {
                return index;
            }
        }
        return FIND_ERROR;
    }
}
