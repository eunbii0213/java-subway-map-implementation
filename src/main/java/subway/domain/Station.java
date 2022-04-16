package subway.domain;

public class Station {
    private static final int MINIMUM_LENGTH = 2;
    private final String name;

    public static Station createStationEntity(String name) {
        try {
            return new Station(name);
        } catch (Exception e) {
            return null;
        }
    }

    private Station(String name) {
        checkName(name);
        this.name = name;
    }

    private void checkName(String name) {
        if (name.length() < MINIMUM_LENGTH) {
            throw new IllegalArgumentException("\n[ERROR] 이름은 두 글자 이상이어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }
}
