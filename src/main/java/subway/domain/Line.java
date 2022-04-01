package subway.domain;

import subway.Checker;

import java.util.LinkedList;

public class Line {
    private String name;
    private LinkedList<Station> subwayMap;
    private static final int SEARCH_ERROR = -1;
    private static final int INITIAL_INDEX = 0;

    public Line(String name) {
        this.name = name;
        subwayMap = new LinkedList<>();
    }

    public int getSubwayMapSize() {
        return subwayMap.size();
    }

    public Station getStationFromSubwayMap(int index) {
        return subwayMap.get(index);
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

    public boolean removeStation(int index, Line line) {
        if (Checker.isLineSizeOverTwo(line)) {
            subwayMap.remove(index);
            return false;
        }
        return true;
    }

    public void addStationInLine(int index, Station station) {
        subwayMap.add(index, station);
    }
}
