package subway;

import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class InitialBeforeStart {

    public void initialLineNameList() {
        LineRepository.addLine(new Line("2호선"));
        LineRepository.addLine(new Line("3호선"));
        LineRepository.addLine(new Line("신분당선"));
    }

    public void initialLineRepository() {
        initialLineNameList();

        Line line = LineRepository.findLineFromLinesUsingUserInput("2호선");

        line.addStationInLine("교대역");
        line.addStationInLine("강남역");
        line.addStationInLine("역삼역");

        line = LineRepository.findLineFromLinesUsingUserInput("3호선");

        line.addStationInLine("교대역");
        line.addStationInLine("남부터미널역");
        line.addStationInLine("양재역");
        line.addStationInLine("매봉역");

        line = LineRepository.findLineFromLinesUsingUserInput("신분당선");

        line.addStationInLine("강남역");
        line.addStationInLine("양재역");
        line.addStationInLine("양재시민의숲역");
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
