package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private static final int initialIndex = 0;
    private static List<String> initialLineNameList = new ArrayList<>();
    private static List<String> initialStationNameList = new ArrayList<>();

    public static List<String> initialStationNamesList() {
        return Collections.unmodifiableList(initialStationNameList);
    }

    public static void initialLineNameList() {
        initialLineNameList.add(INITIAL_FIRST_LINE_NAME);
        initialLineNameList.add(INITIAL_SECOND_LINE_NAME);
        initialLineNameList.add(INITIAL_THIRD_LINE_NAME);
    }

    public static void initialStationNameList() {
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

    public static void initialLineRepository() {
        initialStationNameList();
        initialLineNameList();

        for (int i = initialIndex; i < initialLineNameList.size(); i++) {
            LineRepository.initialLineRepository(initialLineNameList.get(i), i);
        }
    }

    public static void initialStationNameListForStationRepository() {
        initialStationNameList.add(INITIAL_FIRST_STATION);
        initialStationNameList.add(INITIAL_SECOND_STATION);
        initialStationNameList.add(INITIAL_THIRD_STATION);
        initialStationNameList.add(INITIAL_FOURTH_STATION);

        initialStationNameList.add(INITIAL_FIFTH_STATION);
        initialStationNameList.add(INITIAL_SIXTH_STATION);
        initialStationNameList.add(INITIAL_SEVENTH_STATION);
    }

    public static void initialStationRepository() {
        initialStationNameListForStationRepository();

        for (int i = initialIndex; i < initialStationNameList.size(); i++) {
            StationRepository.initialStationRepository(initialStationNameList.get(i));
        }
    }
}
