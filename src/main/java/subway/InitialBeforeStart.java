package subway;

import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.domain.Line;
import subway.domain.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class InitialBeforeStart {
    private static final String INITIAL_FIRST_LINE_NAME = "2호선";
    private static final String INITIAL_SECOND_LINE_NAME = "3호선";
    private static final String INITIAL_THIRD_LINE_NAME = "신분당선";
    private static final String INITIAL_FIRST_STATION = "교대역";
    private static final String INITIAL_SECOND_STATION = "강남역";
    private static final String INITIAL_THIRD_STATION = "역삼역";
    private static final String INITIAL_FOURTH_STATION = "남부터미널역";
    private static final String INITIAL_FIFTH_STATION = "양재역";
    private static final String INITIAL_SIXTH_STATION = "양재시민의숲역";
    private static final String INITIAL_SEVENTH_STATION = "매봉역";
    private static final int INITIAL_INDEX = 0;
    private List<String> initialLineNameList = new ArrayList<>();
    private List<String> initialStationNameList = new ArrayList<>();

    public void initialLineNameList() {
        initialLineNameList.add(INITIAL_FIRST_LINE_NAME);
        initialLineNameList.add(INITIAL_SECOND_LINE_NAME);
        initialLineNameList.add(INITIAL_THIRD_LINE_NAME);
    }

    public void initialStationNameList() {
        initialStationNameList.add(INITIAL_FIRST_STATION + " " +
                INITIAL_SECOND_STATION + " " +
                INITIAL_THIRD_STATION);

        initialStationNameList.add(INITIAL_FIRST_STATION + " " +
                INITIAL_FOURTH_STATION + " " +
                INITIAL_FIFTH_STATION + " " +
                INITIAL_SEVENTH_STATION);

        initialStationNameList.add(INITIAL_SECOND_STATION + " " +
                INITIAL_FIFTH_STATION + " " +
                INITIAL_SIXTH_STATION);
    }

    public void initialLineRepository() {
        initialStationNameList();
        initialLineNameList();

        for (int index = INITIAL_INDEX; index < initialLineNameList.size(); index++) {
            initialLineRepository(initialLineNameList.get(index), index);
        }
    }

    public void initialLineRepository(String lineName, int index) {
        Line line = new Line(lineName);
        LineRepository.addLine(line,lineName);

        StringTokenizer st = new StringTokenizer(initialStationNameList.get(index));
        int stationIndex = INITIAL_INDEX;
        while (st.hasMoreTokens()) {
            line.addStationInLine(stationIndex, new Station(st.nextToken()));
            stationIndex++;
        }
    }

    public void initialStationRepository(String stationName) {
        StationRepository.addStation(new Station(stationName),stationName);
    }

    public void initialStationNameListForStationRepository() {
        initialStationNameList.clear();
        initialStationNameList.add(INITIAL_FIRST_STATION);
        initialStationNameList.add(INITIAL_SECOND_STATION);
        initialStationNameList.add(INITIAL_THIRD_STATION);
        initialStationNameList.add(INITIAL_FOURTH_STATION);

        initialStationNameList.add(INITIAL_FIFTH_STATION);
        initialStationNameList.add(INITIAL_SIXTH_STATION);
        initialStationNameList.add(INITIAL_SEVENTH_STATION);
    }

    public void initialStationRepository() {
        initialStationNameListForStationRepository();

        for (int index = INITIAL_INDEX; index < initialStationNameList.size(); index++) {
            initialStationRepository(initialStationNameList.get(index));
        }
    }
}
