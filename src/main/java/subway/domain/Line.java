package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<Station> subwayMap;
    private static final int SEARCH_ERROR = -1;
    private static final int INITIAL_INDEX = 0;

    public Line(String name) {
        this.name = name;
        subwayMap = new ArrayList<>();
    }

    public List<Station> getSubwayMap() {
        return subwayMap;
    }

    public String getName() {
        return name;
    }

    public int searchTargetStationIndexInSubwayMap(String target) {
        for (int index = INITIAL_INDEX; index < subwayMap.size(); index++) {
            if (subwayMap.get(index).getName().equals(target)) {
                return index;
            }
        }
        return SEARCH_ERROR;
    }

    public void removeStation(int index) {
        subwayMap.remove(index);
    }

    public void changeEndStationAfterRemoveEndStation(int stationIndex) {
        if (stationIndex < StationRepository.stations().size()) {
            Station station = StationRepository.getStations().get(stationIndex + 1);
            subwayMap.add(station);
        }
    }

    public int searchTargetIndexInLine(String target) {
        for (int index = INITIAL_INDEX; index < StationRepository.stations().size(); index++) {
            if (StationRepository.stations().get(index).getName().equals(target)) {
                return index;
            }
        }
        return SEARCH_ERROR;
    }

    public void addStationsInLine(String startTarget, String endTarget) {
        int startTargetIndex = searchTargetIndexInLine(startTarget);
        int endTargetIndex = searchTargetIndexInLine(endTarget);

        for (int index = startTargetIndex; index <= endTargetIndex; index++) {
            subwayMap.add(StationRepository.stations().get(index));
        }
    }
}
