package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();
    private static int initialLinesIndex = 0;

    public LineRepository(){
        lines.add(new Line("2호선"));
        lines.add(new Line("3호선"));
        lines.add(new Line("신분당선"));

        Line line = lines.get(initialLinesIndex++);

        line.getSubwayMap().add(new Station("교대역"));
        line.getSubwayMap().add(new Station("강남역"));
        line.getSubwayMap().add(new Station("역삼역"));

        line = lines.get(initialLinesIndex++);

        line.getSubwayMap().add(new Station("교대역"));
        line.getSubwayMap().add(new Station("남부터미널역"));
        line.getSubwayMap().add(new Station("양재역"));
        line.getSubwayMap().add(new Station("매봉역"));

        line = lines.get(initialLinesIndex);

        line.getSubwayMap().add(new Station("강남역"));
        line.getSubwayMap().add(new Station("양재역"));
        line.getSubwayMap().add(new Station("양재시민의숲역"));
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

    public static Line getLines(int index){
        return lines.get(index);
    }

    public static List<Line> getListLines(){return lines;}
}
