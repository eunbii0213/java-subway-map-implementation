package subway.domain;

public class Section {
    public static final int FIND_ERROR = -1;

    public static int findLineIndexFromLines(LineRepository lineRepository, String userInput) {
        for (int index = 0; index < lineRepository.lines().size(); index++) {
            if (lineRepository.getLines(index).getName().equals(userInput)) {
                return index;
            }
        }
        return FIND_ERROR;
    }

    public static int findStationIndexFromStations(StationRepository stationRepository, String userInput) {
        for (int index = 0; index < stationRepository.getStations().size(); index++) {
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
