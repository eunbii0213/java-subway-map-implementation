package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<Station> subwayMap;
    private static final int SEARCH_ERROR = -1;
    private static final int INNITIAL_INDEX = 0;

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

    public int searchTargetIndexInLine(String target, StationRepository stationRepository) {
        for (int index = INNITIAL_INDEX; index < stationRepository.stations().size(); index++) {
            if (stationRepository.stations().get(index).getName().equals(target)) {
                return index;
            }
        }
        return SEARCH_ERROR;
    }

    public void addStationsInLine(String startTarget, String endTarget, StationRepository stationRepository) {
        int startTargetIndex = searchTargetIndexInLine(startTarget, stationRepository);
        int endTargetIndex = searchTargetIndexInLine(endTarget, stationRepository);

        for (int index = startTargetIndex; index <= endTargetIndex; index++) {
            subwayMap.add(stationRepository.stations().get(index));
        }
    }
}
