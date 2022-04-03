package subway.domain;

import subway.Checker;
import subway.repository.StationRepository;
import subway.view.ErrorView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Line {
    private static final int INITIALIZE_STRING_VARIABLE = 0;
    private static final int SEARCH_ERROR = -1;
    private String name = "";
    private LinkedList<Station> subwayMap;


    public Line(String name) {
        if (Checker.isLengthOverTwo(name) && !Checker.isSameLine(name)) {
            this.name = name;
            subwayMap = new LinkedList<>();
        }
    }

    public List<Station> getSubwayMap() {
        return Collections.unmodifiableList(subwayMap);
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
        for (int index = INITIALIZE_STRING_VARIABLE; index < subwayMap.size(); index++) {
            Station station = subwayMap.get(index);
            if (station.getName().equals(target)) {
                return index;
            }
        }
        return SEARCH_ERROR;
    }

    public boolean removeStation(Line line, String stationName) {
        int stationIndex = searchTargetStationIndexInSubwayMap(stationName);
        boolean isError = Checker.isLineOrStationInputError(stationIndex, false);
        Station station;
        if (!isError) {
            station = getStationFromSubwayMap(stationIndex);
            if (Checker.isLineSizeOverTwo(line)) {
                subwayMap.remove(station);
                return false;
            }
        }
        return true;
    }

    public boolean addStationsInLine(String startTarget, String endTarget) {
        int startStationIndex = StationRepository.findStationFromStations(startTarget);
        boolean isError = Checker.isLineOrStationInputError(startStationIndex, false);

        if (isError) {
            return true;
        }
        int endStationIndex = StationRepository.findStationFromStations(endTarget);
        isError = Checker.isLineOrStationInputError(endStationIndex, false);

        if (isError) {
            return true;
        }

        int startTargetIndex = StationRepository.searchTargetIndex(startTarget);
        int endTargetIndex = StationRepository.searchTargetIndex(endTarget);

        for (int index = startTargetIndex; index <= endTargetIndex; index++) {
            subwayMap.add(StationRepository.getStationFromStations(index));
        }

        return false;
    }

    public boolean addStationInLine(int index, String userInputStationName) {
        if (Checker.isUserNumberInputError(index, subwayMap.size())) {
            return true;
        }

        int stationIndex = StationRepository.findStationFromStations(userInputStationName);
        boolean isError = Checker.isLineOrStationInputError(stationIndex, false);

        if (!isError) {
            isError = Checker.isContainStationInOneLine(userInputStationName, getSubwayMap());
            if (isError) {
                ErrorView.sameStationError();
                return isError;
            }

            Station station = StationRepository.getStationFromStations(stationIndex);
            subwayMap.add(index, station);
        }

        return isError;
    }
}
