package subway;

public class View {
    protected static final String INFO_MESSAGE = "[INFO] ";
    protected static final String ERROR_MESSAGE = "[ERROR] ";
    protected static final String QUIT_GUIDE_MESSAGE = "Q. 종료";
    protected static final String GO_BACK_GUIDE_MESSAGE = "B. 돌아가기";

    protected static final String MAIN_MESSAGE = "## 메인 화면";
    protected static final String STATION_MANAGE_GUIDE_MESSAGE = "1. 역 관리";
    protected static final String LINE_MANAGE_GUIDE_MESSAGE = "2. 노선 관리";
    protected static final String SECTION_MANAGE_GUIDE_MESSAGE = "3. 구간 관리";
    protected static final String SHOW_LINE_GUIDE_MESSAGE = "4. 지하철 노선도 출력";
    protected static final String SELECT_GUIDE_MESSAGE = "## 원하는 기능을 선택하세요.";

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
}
