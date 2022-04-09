package subway;

import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class Initialization {

    public void initializeLineRepository() {
        Station startStation = StationRepository.findStation("교대역");
        Station endStation = StationRepository.findStation("역삼역");
        LineRepository.addLine(Line.createLineEntity("2호선", startStation, endStation));

        startStation = StationRepository.findStation("교대역");
        endStation = StationRepository.findStation("매봉역");
        LineRepository.addLine(Line.createLineEntity("3호선", startStation, endStation));

        startStation = StationRepository.findStation("강남역");
        endStation = StationRepository.findStation("양재시민의숲역");
        LineRepository.addLine(Line.createLineEntity("신분당선", startStation, endStation));
    }

    public void initializeStationRepository() {
        StationRepository.addStation(Station.createStationEntity("교대역"));
        StationRepository.addStation(Station.createStationEntity("강남역"));
        StationRepository.addStation(Station.createStationEntity("역삼역"));
        StationRepository.addStation(Station.createStationEntity("남부터미널역"));

        StationRepository.addStation(Station.createStationEntity("양재역"));
        StationRepository.addStation(Station.createStationEntity("양재시민의숲역"));
        StationRepository.addStation(Station.createStationEntity("매봉역"));
    }
}
