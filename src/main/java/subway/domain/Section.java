package subway.domain;

public class Section {
    private static final int FIND_ERROR = -1;
    private static final int INITIAL_INDEX = 0;

    public static Line findLineIndexFromLines(LineRepository lineRepository, String userInput) {
        for (int index = INITIAL_INDEX; index < lineRepository.lines().size(); index++) {
            if (lineRepository.getLines(index).getName().equals(userInput)) {
                return lineRepository.getLines(index);
            }
        }
        return lineRepository.getLines(FIND_ERROR);
    }

    public static int findStationIndexFromStations(StationRepository stationRepository, String userInput) {
        for (int index = INITIAL_INDEX; index < stationRepository.getStations().size(); index++) {
            if (stationRepository.getStations().get(index).getName().equals(userInput)) {
                return index;
            }
        }
        return FIND_ERROR;
    }

    public static void addStationInLines(int index, Line line,Station station) {
        line.getSubwayMap().add(index,station);
    }
}
