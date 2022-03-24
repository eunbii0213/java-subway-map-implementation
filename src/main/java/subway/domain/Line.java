package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<Station> subwayMap;

    public Line(String name) {
        this.name = name;
        subwayMap = new ArrayList<>();
    }
    public List<Station> getSubwayMap(){
        return subwayMap;
    }

    public String getName() {
        return name;
    }


}
