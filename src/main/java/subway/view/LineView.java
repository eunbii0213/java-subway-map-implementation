package subway.view;

import subway.domain.Line;
import subway.repository.LineRepository;

public class LineView extends View {
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

    public static void showLineMenuGuide() {
        System.out.println();
        System.out.println(AFTER_SELECT_LINE_MANAGE_MESSAGE);
        System.out.println(ADD_LINE_GUIDE_MESSAGE);
        System.out.println(REMOVE_LINE_GUIDE_MESSAGE);
        System.out.println(SHOW_ALL_LINE_GUIDE_MESSAGE);
        System.out.println(GO_BACK_GUIDE_MESSAGE);
        System.out.println();
    }

    public static void showLineInsertNameGuide() {
        System.out.println();
        System.out.println(LINE_INSERT_NAME_GUIDE_MESSAGE);
    }

    public static void showInsertStartStationInLineGuide() {
        System.out.println();
        System.out.println(LINE_INSERT_START_STATION_GUIDE_MESSAGE);
    }

    public static void showInsertEndStationInLineGuide() {
        System.out.println();
        System.out.println(LINE_INSERT_END_STATION_GUIDE_MESSAGE);
    }

    public static void showLineInsertComplete() {
        System.out.println();
        System.out.println(INFO_MESSAGE + LINE_INSERT_COMPLETE_MESSAGE);
    }

    public static void showLineRemoveGuide() {
        System.out.println();
        System.out.println(LINE_REMOVE_GUIDE_MESSAGE);
    }

    public static void showLineRemoveComplete() {
        System.out.println();
        System.out.println(INFO_MESSAGE + LINE_REMOVE_COMPLETE_MESSAGE);
    }

    public static boolean showLineAllMessage() {
        System.out.println();
        System.out.println(LINE_LIST_MESSAGE);

        for (Line line : LineRepository.getLines()) {
            System.out.println(INFO_MESSAGE + line.getName());
        }
        return false;
    }
}
