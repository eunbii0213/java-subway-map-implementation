package subway.view;

public class SectionView extends View {
    private static final String AFTER_SELECT_SECTOR_MANAGE_MESSAGE = "## 구간 관리 화면 ";
    private static final String ADD_SECTOR_GUIDE_MESSAGE = "1. 구간 등록";
    private static final String REMOVE_SECTOR_GUIDE_MESSAGE = "2. 구간 삭제";
    private static final String SECTION_INSERT_NAME_GUIDE_MESSAGE = "## 노선을 입력하세요.";
    private static final String SECTION_INSERT_STATION_NAME_GUIDE_MESSAGE = "## 역이름을 입력하세요.";
    private static final String SECTION_INSERT_NUMBER_GUIDE_MESSAGE = "## 순서를 입력하세요.";
    private static final String SECTION_INSERT_COMPLETE_MESSAGE = "구간이 등록되었습니다.";
    private static final String SECTION_REMOVE_LINE_GUIDE_MESSAGE = "## 삭제할 구간의 노선을 입력하세요.";
    private static final String SECTION_REMOVE_STATION_GUIDE_MESSAGE = "## 삭제할 구간의 역을 입력하세요.";
    private static final String SECTION_REMOVE_COMPLETE_MESSAGE = "구간이 삭제되었습니다.";

    public static void showSectionMenuGuide() {
        System.out.println();
        System.out.println(AFTER_SELECT_SECTOR_MANAGE_MESSAGE);
        System.out.println(ADD_SECTOR_GUIDE_MESSAGE);
        System.out.println(REMOVE_SECTOR_GUIDE_MESSAGE);
        System.out.println(GO_BACK_GUIDE_MESSAGE);
        System.out.println();
    }

    public static void showSectionInsertGuide() {
        System.out.println();
        System.out.println(SECTION_INSERT_NAME_GUIDE_MESSAGE);
    }

    public static void showSectorInsertStationNameGuide() {
        System.out.println();
        System.out.println(SECTION_INSERT_STATION_NAME_GUIDE_MESSAGE);
    }

    public static void showSectorInsertNumberGuide() {
        System.out.println();
        System.out.println(SECTION_INSERT_NUMBER_GUIDE_MESSAGE);
    }

    public static void showSectorInsertComplete() {
        System.out.println();
        System.out.println(INFO_MESSAGE + SECTION_INSERT_COMPLETE_MESSAGE);
    }

    public static void showSectorRemoveLineGuide() {
        System.out.println();
        System.out.println(SECTION_REMOVE_LINE_GUIDE_MESSAGE);
    }

    public static void showSectorRemoveStationGuide() {
        System.out.println();
        System.out.println(SECTION_REMOVE_STATION_GUIDE_MESSAGE);
    }

    public static void showSectorRemoveComplete() {
        System.out.println();
        System.out.println(INFO_MESSAGE + SECTION_REMOVE_COMPLETE_MESSAGE);
    }
}
