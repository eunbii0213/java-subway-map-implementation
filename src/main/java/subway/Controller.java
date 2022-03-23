package subway;

import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.User;

import java.util.Scanner;

public class Controller {
    private static final String OPTION_ONE = "1";
    private static final String OPTION_TWO = "2";
    private static final String OPTION_THREE = "3";
    private static final String OPTION_FOUR = "4";
    private static final String GO_BACK = "B";

    public void startSubwayMap(String input, View view, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        if (input.equals(OPTION_ONE)) {
            stationManage(view, scanner, user, lineRepository, stationRepository);

        }
    }

    public void stationManage(View view, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        while (true) {
            StationView stationView = new StationView();
            stationView.showStationMenuGuide();
            String input = scanner.nextLine();
            if (input.equals(GO_BACK)) {
                break;
            }
            if (input.equals(OPTION_ONE)) {
                //역 등록옵션
                stationView.showStationInsertGuide();
                String userStationNameInput = user.userInput(scanner);
                stationRepository.addStation(new Station(userStationNameInput));
                stationView.showStationInsertComplete();
                continue;
            }
            if (input.equals(OPTION_TWO)) {
                //역 삭제옵션
                stationView.showStationRemoveGuide();
                String userStationInput = user.userInput(scanner);
                stationRepository.deleteStation(userStationInput);
                stationView.showStationRemoveComplete();
                continue;
            }
            if (input.equals(OPTION_THREE)) {
                //역 조회옵션
                stationView.showStationAllMessage(stationRepository);
                continue;
            }

        }//while문 끝
    }
}
