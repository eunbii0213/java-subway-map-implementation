package subway;

public class StationView extends View{
    private static final String AFTER_SELECT_STATION_MANAGE_MESSAGE = "## 역 관리 화면 ";
    private static final String ADD_STATION_GUIDE_MESSAGE = "1. 역 등록";
    private static final String REMOVE_STATION_GUIDE_MESSAGE = "2. 역 삭제";
    private static final String SHOW_ALL_STATIONS_GUIDE_MESSAGE = "3. 역 조회";

    private static final String STATION_LIST_MESSAGE = "## 역 목록";
    private static final String STATION_INSERT_GUIDE_MESSAGE = "## 등록할 역 이름을 입력하세요.";
    private static final String STATION_INSERT_COMPLETE_MESSAGE = "지하철 역이 등록되었습니다.";
    private static final String STATION_REMOVE_GUIDE_MESSAGE = "## 삭제할 역 이름을 입력하세요.";
    private static final String STATION_REMOVE_COMPLETE_MESSAGE = "지하철 역이 삭제되었습니다.";

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
}
