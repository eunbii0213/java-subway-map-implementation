package subway.domain;

import java.util.*;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();
    private static final int INITIAL_INDEX = 0;

    public static void initialLineRepository(String lineName, int index) {
        lines.add(new Line(lineName));
        StringTokenizer st = new StringTokenizer(InitialBeforeStart.initialStationNamesList().get(index));
        int stationIndex = INITIAL_INDEX;
        while (st.hasMoreTokens()) {
            lines.get(index).addStationInLine(stationIndex, new Station(st.nextToken()));
            stationIndex++;
        }
    }

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static Line getLines(int index) {
        return lines.get(index);
    }

    public static void addStationsInLine(int lineIndex, String startTarget, String endTarget) {
        int startTargetIndex = StationRepository.searchTargetIndex(startTarget);
        int endTargetIndex = StationRepository.searchTargetIndex(endTarget);

        Line line = lines.get(lineIndex);
        for (int index = startTargetIndex; index <= endTargetIndex; index++) {
            line.addStationInLine(index, StationRepository.stations().get(index));
        }
    }
}
