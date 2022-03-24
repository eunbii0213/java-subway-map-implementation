package subway;

public class SectionView extends View{
    private static final String AFTER_SELECT_SECTOR_MANAGE_MESSAGE = "## 구간 관리 화면 ";
    private static final String ADD_SECTOR_GUIDE_MESSAGE = "1. 구간 등록";
    private static final String REMOVE_SECTOR_GUIDE_MESSAGE = "2. 구간 삭제";
    private static final String SECTOR_INSERT_NAME_GUIDE_MESSAGE = "## 노선을 입력하세요.";
    private static final String SECTOR_INSERT_STATION_NAME_GUIDE_MESSAGE = "## 역이름을 입력하세요.";
    private static final String SECTOR_INSERT_NUMBER_GUIDE_MESSAGE = "## 순서를 입력하세요.";
    private static final String SECTOR_INSERT_COMPLETE_MESSAGE = "구간이 등록되었습니다.";
    private static final String SECTOR_REMOVE_GUIDE_MESSAGE = "## 삭제할 노선 이름을 입력하세요.";
    private static final String SECTOR_REMOVE_COMPLETE_MESSAGE = "구간이 삭제되었습니다.";

    //3 입력
    public void showSectorMenuGuide() {
        System.out.println();
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
