package subway.domain;

import subway.repository.StationRepository;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Line {
    private static final int INITIALIZE_STRING_VARIABLE = 0;
    private static final int MINIMUM_LENGTH = 2;
    private static final int LAST_INDEX = 1;
    private String name = "";
    private LinkedList<Station> subwayMap;

    public static Line createLineEntity(String name, Station startStation, Station endStation) {
        try {
            Line line = new Line(name, startStation, endStation);
            line.addStationsInLine(startStation.getName(), endStation.getName());
            return line;
        } catch (Exception e) {
            return null;
        }
    }

    private Line(String name, Station startStation, Station endStation) {
        checkName(name);
        requireNonNull(startStation, endStation);
        this.name = name;
        subwayMap = new LinkedList<>();
    }

    private void requireNonNull(Object... args) {
        for (Object object : args) {
            if (object == null) {
                throw new IllegalArgumentException("\n[ERROR] 올바른 역 이름을 입력해주세요.");
            }
        }
    }

    private void checkName(String name) {
        if (name.length() < MINIMUM_LENGTH) {
            throw new IllegalArgumentException("\n[ERROR] 이름은 두 글자 이상이어야 합니다.");
        }
    }

    public List<Station> getSubwayMap() {
        return Collections.unmodifiableList(subwayMap);
    }

    public Station getStationFromSubwayMap(int index) {
        return subwayMap.get(index);
    }

    public String getName() {
        return name;
    }

    public int searchTargetStationIndexInSubwayMap(String target) {
        int index = INITIALIZE_STRING_VARIABLE;
        while (index < subwayMap.size()) {
            Station station = subwayMap.get(index);
            if (station.getName().equals(target)) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("\n[ERROR] 올바른 역 이름을 입력해주세요.");
    }

    public void removeStation(String stationName) {
        try {
            int stationIndex = searchTargetStationIndexInSubwayMap(stationName);
            Station station = getStationFromSubwayMap(stationIndex);

            if (subwayMap.size() <= MINIMUM_LENGTH) {
                throw new IllegalArgumentException("\n[ERROR] 역이 두 개 이하인 노선은 역을 삭제할 수 없습니다.");
            }
            subwayMap.remove(station);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void addStationsInLine(String startTarget, String endTarget) {
        try {
            int startStationIndex = StationRepository.findStationIndex(startTarget);
            int endStationIndex = StationRepository.findStationIndex(endTarget);

            IntStream.rangeClosed(startStationIndex, endStationIndex).forEachOrdered(index -> subwayMap.add(StationRepository.getStation(index)));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void checkUserIndexNumberInput(int index) {
        if (index > subwayMap.size()) {
            throw new IllegalArgumentException("\n[ERROR] 올바른 순서를 입력하세요.");
        }
    }

    public void addStationInLine(int index, String userInputStationName) {
        try {
            checkUserIndexNumberInput(index);
            Station station = StationRepository.findStation(userInputStationName);
            subwayMap.add((index - LAST_INDEX), station);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void checkStationExistInLine(String stationName) {
        for (Station compareStation : subwayMap) {
            if (compareStation.getName().equals(stationName)) {
                throw new IllegalArgumentException("\n[ERROR] 노선에 추가된 역은 제거할 수 없습니다.");
            }
        }
    }

    public void checkStationExistInSubwayMap(String stationName) {
        for (Station compareStation : subwayMap) {
            if (compareStation.getName().equals(stationName)) {
                throw new IllegalArgumentException("\n[ERROR] 이미 등록된 역 입니다.");
            }
        }
    }
}
