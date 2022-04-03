package subway;

import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class InitialBeforeStart {
    private static final int INITIALIZE_STRING_VARIABLE = 0;

    public void initialLineNameList() {
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("3호선"));
        LineRepository.addLine(new Line("신분당선"));
    }

    public void initialLineRepository() {
        initialLineNameList();

        int index = INITIALIZE_STRING_VARIABLE;
        int stationIndex = INITIALIZE_STRING_VARIABLE;
        System.out.println(LineRepository.getLines(index).getName());
        LineRepository.getLines(index).addStationInLine(stationIndex++, "교대역");
        LineRepository.getLines(index).addStationInLine(stationIndex++, "강남역");
        LineRepository.getLines(index++).addStationInLine(stationIndex, "역삼역");

        stationIndex = INITIALIZE_STRING_VARIABLE;

        LineRepository.getLines(index).addStationInLine(stationIndex++, "교대역");
        LineRepository.getLines(index).addStationInLine(stationIndex++, "남부터미널역");
        LineRepository.getLines(index).addStationInLine(stationIndex++, "양재역");
        LineRepository.getLines(index++).addStationInLine(stationIndex++, "매봉역");

        stationIndex = INITIALIZE_STRING_VARIABLE;

        LineRepository.getLines(index).addStationInLine(stationIndex++, "강남역");
        LineRepository.getLines(index).addStationInLine(stationIndex++, "양재역");
        LineRepository.getLines(index).addStationInLine(stationIndex++, "양재시민의숲역");

    }

    public void initialStationRepository() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));

        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
        StationRepository.addStation(new Station("매봉역"));
    }
}
