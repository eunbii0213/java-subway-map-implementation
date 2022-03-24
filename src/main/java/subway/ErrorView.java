package subway;

public class ErrorView extends View {
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String REMOVE_ERROR_MESSAGE = "노선에 추가된 역은 제거할 수 없습니다.";
    private static final String USER_INPUT_ERROR_MESSAGE = "선택할 수 없는 기능입니다.";
    private static final String ADD_STATION_ERROR_MESSAGE= "이미 등록된 역 이름입니다.";
    private static final String USER_INPUT_LENGTH_ERROR_MESSAGE = "역의 이름은 두 글자 이상이어야 합니다.";
    public static void removeErrorStationInLine() {
        System.out.println(ERROR_MESSAGE + REMOVE_ERROR_MESSAGE);
    }

    public static void userInputError() {
       System.out.println(ERROR_MESSAGE + USER_INPUT_ERROR_MESSAGE);
    }

    public static void userInputLengthError(){
        System.out.println(ERROR_MESSAGE + USER_INPUT_LENGTH_ERROR_MESSAGE);
    }

    public static void addSameStationError(){
        System.out.println(ERROR_MESSAGE+ ADD_STATION_ERROR_MESSAGE);

    }

}
