package subway.repository;

import subway.domain.Line;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LineRepository {
    private static final int INITIALIZE_STRING_VARIABLE = 0;
    private static final LinkedList<Line> lines = new LinkedList<>();

    public static List<Line> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        try {
            checkNull(line);
            checkSameLine(line);
            lines.add(line);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static void checkNull(Line line) {
        if (Objects.isNull(line)) {
            throw new IllegalArgumentException("\n[ERROR] 올바른 노선 이름을 입력해주세요.");
        }
    }

    private static void checkSameLine(Line line) {
        lines.stream()
                .filter(it -> it.getName().equals(line.getName()))
                .forEach(it -> {
                    throw new IllegalArgumentException("\n[ERROR] 이미 등록된 노선 이름입니다.");
                });
    }

    public static void deleteLineByName(String name) {
        try {
            checkLineName(name);
            lines.removeIf(line -> Objects.equals(line.getName(), name));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static Line getLines(int index) {
        return lines.get(index);
    }

    public static int findLineIndexFromLines(String userInput) {
        int index = INITIALIZE_STRING_VARIABLE;
        while (index < lines.size()) {
            if (lines.get(index).getName().equals(userInput)) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("\n[ERROR] 올바른 노선 이름을 입력해주세요.");
    }

    public static void checkLineName(String userInput) {
        boolean rightLineName = false;
        int index = INITIALIZE_STRING_VARIABLE;
        while (index < lines.size()) {
            if (lines.get(index).getName().equals(userInput)) {
                rightLineName = true;
            }
            index++;
        }
        if (!rightLineName) {
            throw new IllegalArgumentException("\n[ERROR] 올바른 노선 이름을 입력해주세요.");
        }
    }
}
