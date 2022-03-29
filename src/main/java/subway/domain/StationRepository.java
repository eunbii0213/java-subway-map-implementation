package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();
    private static final int INITIAL_INDEX = 0;
    private static final int SEARCH_ERROR = -1;

    public static void initialStationRepository(String stationName) {
        stations.add(new Station(stationName));
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static int searchTargetIndex(String target) {
        for (int index = INITIAL_INDEX; index < stations.size(); index++) {
            if (stations.get(index).getName().equals(target)) {
                return index;
            }
        }
        return SEARCH_ERROR;
    }
}
