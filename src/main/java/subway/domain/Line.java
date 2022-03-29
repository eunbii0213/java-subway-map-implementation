package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
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

    public List<Station> subwayMapList() {
        return Collections.unmodifiableList(subwayMap);
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

    public void addStationInLine(int index, Station station) {
        subwayMap.add(index, station);
    }
}
