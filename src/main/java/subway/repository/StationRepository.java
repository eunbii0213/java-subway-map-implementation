package subway.repository;

import subway.domain.Station;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StationRepository {
    private static final LinkedList<Station> stations = new LinkedList<>();
    private static final int INITIALIZE_STRING_VARIABLE = 0;

    public static List<Station> getStations() {
        return Collections.unmodifiableList(stations);
    }

    public static Station getStation(int index) {
        return stations.get(index);
    }

    public static void addStation(Station station) {
        if (Objects.isNull(station)) {
            throw new IllegalArgumentException("\n[ERROR] 올바른 역 이름을 입력해주세요.");
        }

        stations.stream().filter(nowStation -> nowStation
                        .getName().equals(station.getName()))
                .forEachOrdered(nowStation -> {
                    throw new IllegalArgumentException("\n[ERROR] 이미 추가된 역입니다.");
                });
        stations.add(station);
    }

    public static void deleteStation(String name) {
        try {
            StationRepository.findStationIndex(name);
            LineRepository.getLines().forEach(line -> line.checkStationExistInLine(name));

            stations.removeIf(station -> Objects.equals(station.getName(), name));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static Station findStation(String userInput) {
        for (Station station : stations) {
            if (station.getName().equals(userInput)) {
                return station;
            }
        }
        throw new IllegalArgumentException("\n[ERROR] 올바른 역 이름을 입력해주세요.");
    }

    public static int findStationIndex(String userInput) {
        int index = INITIALIZE_STRING_VARIABLE;
        while (index < stations.size()) {
            if (stations.get(index).getName().equals(userInput)) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("\n[ERROR] 올바른 역 이름을 입력해주세요.");
    }
}
