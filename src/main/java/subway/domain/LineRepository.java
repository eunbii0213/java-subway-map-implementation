package subway.domain;

import subway.Checker;

import java.util.LinkedList;
import java.util.Objects;

public class LineRepository {
    private static final int INITIAL_INDEX = 0;
    private static LinkedList<Line> lines = new LinkedList<>();

    public static int getLinesSize() {
        return lines.size();
    }

    public static boolean addLine(Line line, String userLineNameInput) {
        if (!Checker.isLengthOverTwo(userLineNameInput) || Checker.isSameLine(userLineNameInput)) {
            return true;
        }
        lines.add(line);
        return false;
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static Line getLines(int index) {
        return lines.get(index);
    }

    public static void addStationsInLine(String startTarget, String endTarget) {
        int startTargetIndex = StationRepository.searchTargetIndex(startTarget);
        int endTargetIndex = StationRepository.searchTargetIndex(endTarget);
        Line line = lines.getLast();
        int lineStationInputIndex = INITIAL_INDEX;

        for (int index = startTargetIndex; index <= endTargetIndex; index++) {
            line.addStationInLine(lineStationInputIndex, StationRepository.getStationFromStations(index));
            lineStationInputIndex++;
        }
    }
}
