# 지하철 노선도 미션
- 지하철 역과 노선을 관리하는 지하철 노선도 기능을 구현한다.

<br>

## 🚀 기능 요구사항

### 초기 설정 
- 프로그램 시작 시 역, 노선 등 필요한 정보를 미리 셋팅할 수 있다.

> 아래의 사전 등록 정보로 반드시 초기 설정을 하기
>
```
 1. 지하철역으로 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역이 등록되어 있다.
 2. 지하철 노선으로 2호선, 3호선, 신분당선이 등록되어 있다.
 3. 노선에 역이 아래와 같이 등록되어 있다.(왼쪽 끝이 상행 종점)
   - 2호선: 교대역 - 강남역 - 역삼역
   - 3호선: 교대역 - 남부터미널역 - 양재역 - 매봉역
   - 신분당선: 강남역 - 양재역 - 양재시민의숲역
 ```

<img src="image/domain.png" width="500">

0.  메뉴들 출력 메세지 설정
1. 사전 등록 정보로 초기 설정
2. 지하철 역 관련기능
  1. 역 등록, 삭제 가능
  2. 노선에 등록된 역은 삭제 불가 기능 추가
  3. 지하철 역 이름은 중복 불가.
  4. 지하철 역 이름은 2글자 이상이어야 한다.
  5. 지하철 역의 목록을 조회할 수 있다.
3. 지하철 노선 관련기능 (노선은 구간들의 모음이다)
  1. 노선 등록, 삭제 가능
  2. 중복된 지하철 노선 이름 등록 불가
  3. 지하철 노선 이름은 2글자 이상이어야 한다.
  4. 노선 등록시 상행 종점역과 하행 종점역을 입력받는다.
  5. 지하철 노선의 목록 조회가능
4. 지하철 구간 관련 기능 (역과 역 사이를 구간이라고 한다)
  1. 노선에 구간 추가 가능(노선에 구간 추가 == 노선에 역을 추가)
  2. 노선에 구간 삭제 기능 (종점을 제거할 경우 다음 역이 종점이 됨),(노선에 포함된 역이 두 개 이하일 때는 역 제거 불가)

## 📈 진행 요구사항
- 미션은 [java-subway-map-precourse 저장소](https://github.com/kth496/java-subway-map-implementation) 를 fork/clone해 시작한다.
- 기능을 구현하기 전에 java-subway-map-precourse/docs/README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.
  - [AngularJS Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 참고해 commit log를 남긴다.
<br>

## 📝 License

This project is [MIT](https://github.com/kth496/java-subway-map-implementation/blob/master/LICENSE.md) licensed.
