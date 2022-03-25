package subway;

import subway.domain.*;

import java.util.Scanner;

public class Controller {
    private static final String OPTION_ONE = "1";
    private static final String OPTION_TWO = "2";
    private static final String OPTION_THREE = "3";
    private static final String GO_BACK = "B";
    private static final int STATION_OPTION_START = 1;
    private static final int STATION_OPTION_END = 3;
    private static final int LINE_OPTION_START = 1;
    private static final int LINE_OPTION_END = 3;
    private static final int SECTION_OPTION_START = 1;
    private static final int SECTION_OPTION_END = 2;

    public void startSubwayMap(String input, Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        if (input.equals(OPTION_ONE)) {
            stationManage(scanner, user, lineRepository, stationRepository);
        }
        if (input.equals(OPTION_TWO)) {
            lineManage(scanner, user, lineRepository, stationRepository);
        }
        if (input.equals(OPTION_THREE)) {
            sectionManage(scanner, user, lineRepository, stationRepository);
        }
    }

    private void sectionManage(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        Checker checker = new Checker();
        SectionView sectionView = new SectionView();
        sectionView.showSectorMenuGuide();
        Section section = new Section();

        while (true) {
            String input = user.userInput(scanner);

            if (input.equals(GO_BACK)) {
                break;
            }

            if (checker.checkUserInputIsNotValid(input, SECTION_OPTION_START, SECTION_OPTION_END)) {
                sectionView.showSelectGuideMessage();
                continue;
            }

            if (input.equals(OPTION_ONE)) {
                sectionView.showSectorInsertGuide();

                String userInputLineName = user.userInput(scanner);
                int lineIndex = section.findLineIndexFromLines(lineRepository, userInputLineName);
                Line line = lineRepository.getLines(lineIndex);

                sectionView.showSectorInsertStationNameGuide();
                String userInputStationName = user.userInput(scanner);
                int stationIndex = section.findStationIndexFromStations(stationRepository, userInputStationName);
                Station station = stationRepository.getStations().get(stationIndex);

                sectionView.showSectorInsertNumberGuide();
                //System.out.println("hihihi");
                String userInputIndex = user.userInput(scanner);
                //System.out.println("userInputIndex 받음");

                int index = Integer.parseInt(userInputIndex) - 1;
                section.addStationInLines(index, line, station);
                //System.out.println("add end");

                sectionView.showSectorInsertComplete();
                break;
            }
            if (input.equals(OPTION_TWO)) {
                sectionView.showSectorRemoveLineGuide();

                String userInputLineName = user.userInput(scanner);
                int lineIndex = section.findLineIndexFromLines(lineRepository, userInputLineName);
                Line line = lineRepository.getLines(lineIndex);

                sectionView.showSectorRemoveStationGuide();
                String userInputStationName = user.userInput(scanner);
                int stationIndex = section.findStationIndexFromStations(stationRepository, userInputStationName);
                Station station = stationRepository.getStations().get(stationIndex);


                int index = line.searchTargetLineIndexInSubwayMap(station.getName()) - 1;

                if (!checker.isLineSizeOverTwo(line)) {
                    sectionView.showSelectGuideMessage();
                    continue;
                }

                if (checker.isLastStation(line, index)) {
                    line.changeEndStationAfterRemoveEndStation(index, stationIndex, stationRepository);
                }

                line.removeStation(index);

                sectionView.showSectorRemoveComplete();
                break;
            }

        }

    }

    public void lineManage(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        Checker checker = new Checker();
        LineView lineView = new LineView();
        lineView.showLineMenuGuide();

        while (true) {
            String input = user.userInput(scanner);

            if (input.equals(GO_BACK)) {
                break;
            }

            if (checker.checkUserInputIsNotValid(input, LINE_OPTION_START, LINE_OPTION_END)) {
                lineView.showSelectGuideMessage();
                continue;
            }

            if (input.equals(OPTION_ONE)) {

                if (lineOptionOne(lineView, checker, user, scanner, lineRepository, stationRepository)) {
                    break;
                }
                lineView.showLineMenuGuide();
                continue;
            }
            if (input.equals(OPTION_TWO)) {
                //노선 삭제 옵션
                if (lineOptionTwo(lineView, checker, user, scanner, stationRepository, lineRepository)) {
                    break;
                }
                lineView.showLineMenuGuide();
                continue;
            }
            if (input.equals(OPTION_THREE)) {
                lineView.showLineAllMessage(lineRepository);
                break;
            }
        }
    }

    //error 발생시 false return
    public boolean lineOptionOne(LineView lineView, Checker checker, User user, Scanner scanner, LineRepository lineRepository, StationRepository stationRepository) {
        //노선 등록옵션
        lineView.showLineInsertNameGuide();
        String userLineNameInput = user.userInput(scanner);

        if (!checker.isLengthOverTwo(userLineNameInput)) {
            return false;
        }

        if (!checker.isSameLine(userLineNameInput, lineRepository)) {
            lineRepository.addLine(new Line(userLineNameInput));

            lineView.showInsertStartStationInLineGuide();
            String startStation = user.userInput(scanner);
            lineView.showInsertEndStationInLineGuide();
            String endStation = user.userInput(scanner);

            lineRepository.getListLines().get(lineRepository.lines().size() - 1).addStationsInLine(startStation, endStation, stationRepository);

            lineView.showLineInsertComplete();
            return true;
        }
        return false;
    }

    public boolean lineOptionTwo(LineView lineView, Checker checker, User user, Scanner scanner, StationRepository stationRepository, LineRepository lineRepository) {
        //노선 삭제옵션 ++ 체크 후 삭제 필요 (
        lineView.showLineRemoveGuide();
        String userRemoveLineInput = user.userInput(scanner);
        if (!checker.isSameLine(userRemoveLineInput, lineRepository)) {
            lineRepository.deleteLineByName(userRemoveLineInput);
            lineView.showLineRemoveComplete();
            return true;
        }
        return false;
    }


    public void stationManage(Scanner scanner, User user, LineRepository lineRepository, StationRepository stationRepository) {
        Checker checker = new Checker();
        StationView stationView = new StationView();
        stationView.showStationMenuGuide();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals(GO_BACK)) {
                break;
            }

            if (checker.checkUserInputIsNotValid(input, STATION_OPTION_START, STATION_OPTION_END)) {
                stationView.showSelectGuideMessage();
                continue;
            }
            if (input.equals(OPTION_ONE)) {
                stationOptionOne(stationView, checker, user, scanner, stationRepository);
                break;
            }
            if (input.equals(OPTION_TWO)) {
                stationOptionTwo(stationView, checker, user, scanner, stationRepository, lineRepository);
                break;
            }
            if (input.equals(OPTION_THREE)) {
                stationView.showStationAllMessage(stationRepository);
                break;
            }
        }
    }

    public void stationOptionOne(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository) {
        //역 등록옵션
        stationView.showStationInsertGuide();
        String userStationNameInput = user.userInput(scanner);
        if (checker.isLengthOverTwo(userStationNameInput)) {
            if (!checker.isSameName(userStationNameInput, stationRepository)) {
                stationRepository.addStation(new Station(userStationNameInput));
                stationView.showStationInsertComplete();
            }
        }
    }

    public void stationOptionTwo(StationView stationView, Checker checker, User user, Scanner scanner, StationRepository stationRepository, LineRepository lineRepository) {
        stationView.showStationRemoveGuide();
        String userStationInput = user.userInput(scanner);
        if (!checker.isContainStationInLine(lineRepository, userStationInput)) {
            stationRepository.deleteStation(userStationInput);
            stationView.showStationRemoveComplete();
        }
    }
}
