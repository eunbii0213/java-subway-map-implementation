package subway.domain;

import subway.Checker;

import java.util.*;

public class StationRepository {
    private static final LinkedList<Station> stations = new LinkedList<>();
    private static final int INITIAL_INDEX = 0;
    private static final int SEARCH_ERROR = -1;

    public static int getStationSize(){
        return stations.size();
    }

    public static Station getStationFromStations(int index){
        return stations.get(index);
    }

    public static boolean addStation(Station station, String userStationNameInput) {
        if (!Checker.isLengthOverTwo(userStationNameInput) || Checker.isSameName(userStationNameInput)) {
            return true;
        }
        stations.add(station);
        return false;
    }

    public static boolean deleteStation(String name) {
        if (Checker.isContainStationInLine(name)) {
            return true;
        }
        stations.removeIf(station -> Objects.equals(station.getName(), name));
        return false;
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
