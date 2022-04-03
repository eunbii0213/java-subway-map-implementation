package subway.repository;

import subway.Checker;
import subway.domain.Station;
import subway.view.ErrorView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class StationRepository {
    private static final LinkedList<Station> stations = new LinkedList<>();
    private static final int INITIALIZE_STRING_VARIABLE = 0;
    private static final int SEARCH_ERROR = -1;

    public static List<Station> getStations() {
        return Collections.unmodifiableList(stations);
    }

    public static Station getStationFromStations(int index) {
        return stations.get(index);
    }

    public static boolean addStation(Station station) {
        if (station.getName().equals("")) {
            return true;
        }
        stations.add(station);
        return false;
    }

    public static boolean deleteStation(String name) {
        int stationIndex = StationRepository.searchTargetIndex(name);
        boolean isError = Checker.isLineOrStationInputError(stationIndex, false);

        if (!isError) {
            if (Checker.isContainStationInLine(name)) {
                ErrorView.removeErrorStationInLine();
                return true;
            }
            stations.removeIf(station -> Objects.equals(station.getName(), name));
        }
        return isError;
    }

    public static int searchTargetIndex(String target) {
        return IntStream.range(INITIALIZE_STRING_VARIABLE, stations.size()).filter(index -> stations.get(index).getName().equals(target)).findFirst().orElse(SEARCH_ERROR);
    }

    public static int findStationFromStations(String userInput) {

        for (int index = INITIALIZE_STRING_VARIABLE; index < stations.size(); index++) {
            if (stations.get(index).getName().equals(userInput)) {
                return index;
            }
        }
        return -1;
    }
}
