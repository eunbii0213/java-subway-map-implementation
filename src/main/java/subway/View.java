package subway;

public class View {
    private static final String INFO_MESSAGE = "[INFO] ";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String QUIT_GUIDE_MESSAGE = "Q. 종료";
    private static final String GO_BACK_GUIDE_MESSAGE = "B. 돌아가기";

    private static final String MAIN_MESSAGE = "## 메인 화면";
    private static final String STATION_MANAGE_GUIDE_MESSAGE = "1. 역 관리";
    private static final String LINE_MANAGE_GUIDE_MESSAGE = "2. 노선 관리";
    private static final String SECTION_MANAGE_GUIDE_MESSAGE = "3. 구간 관리";
    private static final String SHOW_LINE_GUIDE_MESSAGE = "4. 지하철 노선도 출력";
    private static final String SELECT_GUIDE_MESSAGE = "## 원하는 기능을 선택하세요.";

    private static final String AFTER_SELECT_STATION_MANAGE_MESSAGE = "## 역 관리 화면 ";
    private static final String ADD_STATION_GUIDE_MESSAGE = "1. 역 등록";
    private static final String REMOVE_STATION_GUIDE_MESSAGE = "2. 역 삭제";
    private static final String SHOW_ALL_STATIONS_GUIDE_MESSAGE = "3. 역 조회";

    private static final String STATION_LIST_MESSAGE = "## 역 목록";
    private static final String STATION_INSERT_GUIDE_MESSAGE = "## 등록할 역 이름을 입력하세요.";
    private static final String STATION_INSERT_COMPLETE_MESSAGE = "지하철 역이 등록되었습니다.";
    private static final String STATION_REMOVE_GUIDE_MESSAGE = "## 삭제할 역 이름을 입력하세요.";
    private static final String STATION_REMOVE_COMPLETE_MESSAGE = "지하철 역이 삭제되었습니다.";

    private static final String AFTER_SELECT_LINE_MANAGE_MESSAGE = "## 노선 관리 화면 ";
    private static final String ADD_LINE_GUIDE_MESSAGE = "1. 노선 등록";
    private static final String REMOVE_LINE_GUIDE_MESSAGE = "2. 노선 삭제";
    private static final String SHOW_ALL_LINE_GUIDE_MESSAGE = "3. 노선 조회";

    private static final String LINE_LIST_MESSAGE = "## 노선 목록";
    private static final String LINE_INSERT_NAME_GUIDE_MESSAGE = "## 등록할 노선 이름을 입력하세요.";
    private static final String LINE_INSERT_START_STATION_GUIDE_MESSAGE = "## 등록할 노선의 상행 종점역 이름을 입력하세요.";
    private static final String LINE_INSERT_END_STATION_GUIDE_MESSAGE = "## 등록할 노선의 하행 종점역 이름을 입력하세요.";
    private static final String LINE_INSERT_COMPLETE_MESSAGE = "지하철 노선이 등록되었습니다.";
    private static final String LINE_REMOVE_GUIDE_MESSAGE = "## 삭제할 노선 이름을 입력하세요.";
    private static final String LINE_REMOVE_COMPLETE_MESSAGE = "지하철 노선이 삭제되었습니다.";

    private static final String AFTER_SELECT_SECTOR_MANAGE_MESSAGE = "## 구간 관리 화면 ";
    private static final String ADD_SECTOR_GUIDE_MESSAGE = "1. 구간 등록";
    private static final String REMOVE_SECTOR_GUIDE_MESSAGE = "2. 구간 삭제";

    private static final String SECTOR_INSERT_NAME_GUIDE_MESSAGE = "## 노선을 입력하세요.";
    private static final String SECTOR_INSERT_STATION_NAME_GUIDE_MESSAGE = "## 역이름을 입력하세요.";
    private static final String SECTOR_INSERT_NUMBER_GUIDE_MESSAGE = "## 순서를 입력하세요.";
    private static final String SECTOR_INSERT_COMPLETE_MESSAGE = "구간이 등록되었습니다.";
    private static final String SECTOR_REMOVE_GUIDE_MESSAGE = "## 삭제할 노선 이름을 입력하세요.";
    private static final String SECTOR_REMOVE_COMPLETE_MESSAGE = "구간이 삭제되었습니다.";

    //일단 메뉴들만 다 만들어놓자. 추가로 4번 입력받았을 때, ErrorMessage 구현도 필요
    public void showMainGuide() {
        System.out.println(MAIN_MESSAGE);
        System.out.println(STATION_MANAGE_GUIDE_MESSAGE);
        System.out.println(LINE_MANAGE_GUIDE_MESSAGE);
        System.out.println(SECTION_MANAGE_GUIDE_MESSAGE);
        System.out.println(SHOW_LINE_GUIDE_MESSAGE);
        System.out.println(QUIT_GUIDE_MESSAGE);
        System.out.println();
        System.out.println(SELECT_GUIDE_MESSAGE);
    }

    //1 입력
    public void showStationMenuGuide() {
        System.out.println(AFTER_SELECT_STATION_MANAGE_MESSAGE);
        System.out.println(ADD_STATION_GUIDE_MESSAGE);
        System.out.println(REMOVE_STATION_GUIDE_MESSAGE);
        System.out.println(SHOW_ALL_STATIONS_GUIDE_MESSAGE);
        System.out.println(GO_BACK_GUIDE_MESSAGE);
        System.out.println();
        System.out.println(SELECT_GUIDE_MESSAGE);
    }

    //1->1 입력
    public void showStationInsertGuide() {
        System.out.println(STATION_INSERT_GUIDE_MESSAGE);
    }

    //1->1 입력
    public void showStationInsertComplete() {
        System.out.println(INFO_MESSAGE + STATION_INSERT_COMPLETE_MESSAGE);
    }

    //1->2 입력
    public void showStationRemoveGuide() {
        System.out.println(STATION_REMOVE_GUIDE_MESSAGE);
    }

    //1->2 입력
    public void showStationRemoveComplete() {
        System.out.println(INFO_MESSAGE + STATION_REMOVE_COMPLETE_MESSAGE);
    }

    //1->3 입력. 추가 구현 필요.
    public void showStationAllMessage() {
        System.out.println(STATION_LIST_MESSAGE);
    }

    //2 입력
    public void showLineMenuGuide() {
        System.out.println(AFTER_SELECT_LINE_MANAGE_MESSAGE);
        System.out.println(ADD_LINE_GUIDE_MESSAGE);
        System.out.println(REMOVE_LINE_GUIDE_MESSAGE);
        System.out.println(SHOW_ALL_LINE_GUIDE_MESSAGE);
        System.out.println(GO_BACK_GUIDE_MESSAGE);
        System.out.println();
        System.out.println(SELECT_GUIDE_MESSAGE);
    }

    //2->1 입력
    public void showLineInsertNameGuide() {
        System.out.println(LINE_INSERT_NAME_GUIDE_MESSAGE);
    }

    //2->1 입력
    public void showInsertStartStationInLineGuide() {
        System.out.println(LINE_INSERT_START_STATION_GUIDE_MESSAGE);
    }

    //2->1 입력
    public void showInsertEndStationInLineGuide() {
        System.out.println(LINE_INSERT_END_STATION_GUIDE_MESSAGE);
    }

    //2->1 입력
    public void showLineInsertComplete() {
        System.out.println(INFO_MESSAGE + LINE_INSERT_COMPLETE_MESSAGE);
    }

    //2->2 입력
    public void showLineRemoveGuide() {
        System.out.println(LINE_REMOVE_GUIDE_MESSAGE);
    }

    //2->2 입력
    public void showLineRemoveComplete() {
        System.out.println(INFO_MESSAGE + LINE_REMOVE_COMPLETE_MESSAGE);
    }

    //2->3 입력. 추가구현 필요
    public void showLineAllMessage() {
        System.out.println(LINE_LIST_MESSAGE);
    }

    //3 입력
    public void showSectorMenuGuide() {
        System.out.println(AFTER_SELECT_SECTOR_MANAGE_MESSAGE);
        System.out.println(ADD_SECTOR_GUIDE_MESSAGE);
        System.out.println(REMOVE_SECTOR_GUIDE_MESSAGE);
        System.out.println(GO_BACK_GUIDE_MESSAGE);
        System.out.println();
        System.out.println(SELECT_GUIDE_MESSAGE);
    }

    //3->1 입력
    public void showSectorInsertGuide() {
        System.out.println(SECTOR_INSERT_NAME_GUIDE_MESSAGE);
    }

    //3->1 입력
    public void showSectorInsertStationNameGuide() {
        System.out.println(SECTOR_INSERT_STATION_NAME_GUIDE_MESSAGE);
    }

    //3->1 입력
    public void showSectorInsertNumberGuide() {
        System.out.println(SECTOR_INSERT_NUMBER_GUIDE_MESSAGE);
    }

    public void showSectorInsertComplete() {
        System.out.println(INFO_MESSAGE + SECTOR_INSERT_COMPLETE_MESSAGE);
    }

    //3->2 입력
    public void showSectorRemoveGuide() {
        System.out.println(SECTOR_REMOVE_GUIDE_MESSAGE);
    }

    //3->2 입력
    public void showSectorRemoveComplete() {
        System.out.println(INFO_MESSAGE + SECTOR_REMOVE_COMPLETE_MESSAGE);
    }
}
