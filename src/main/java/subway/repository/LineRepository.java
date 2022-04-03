package subway.repository;

import subway.Checker;
import subway.domain.Line;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LineRepository {
    private static final int FIND_ERROR = -1;
    private static final int INITIALIZE_STRING_VARIABLE = 0;
    private static LinkedList<Line> lines = new LinkedList<>();

    public static List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public static boolean addLine(Line line) {
        if (line.getName().equals("")) {
            return true;
        }
        lines.add(line);
        return false;
    }

    public static boolean deleteLineByName(String name) {
        int lineIndex = LineRepository.findLineIndexFromLines(name);
        boolean isError = Checker.isLineOrStationInputError(lineIndex, true);

        if (!isError) {
            Line nowLine = lines.get(lineIndex);
            isError = !(Checker.isLineSizeOverTwo(nowLine));
            if (!isError) {
                lines.removeIf(line -> Objects.equals(line.getName(), name));
            }
        }
        return isError;
    }

    public static Line getLines(int index) {
        return lines.get(index);
    }

    public static Line getLastLine() {
        return lines.getLast();
    }

    public static void removeLastLine() {
        lines.remove(lines.getLast());
    }

    public static int findLineIndexFromLines(String userInput) {

        for (int index = INITIALIZE_STRING_VARIABLE; index < lines.size(); index++) {
            if (lines.get(index).getName().equals(userInput)) {
                return index;
            }
        }
        return FIND_ERROR;
    }
}
