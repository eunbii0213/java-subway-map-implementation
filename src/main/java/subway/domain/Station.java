package subway.domain;

import subway.Checker;

public class Station {
    private String name = "";

    public Station(String name) {
        if (Checker.isLengthOverTwo(name) && !Checker.isSameName(name)) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }
}
