# DataCommunicationProject
2023학년도 3-2 데이터통신설계 프로젝트 백엔드 저장소

## 백엔드 기능
1. 특정 공연에 대한 정보를 DB에 저장 및 관리
2. 특정 공연의 특정 좌석의 예매가능 여부를 클라이언트에게 반환
3. 저장된 공연의 이미지 및 기타 정보 제공
4. 예매 시 NFT 토큰을 발행할 수 있는 토큰 값 반환

## ERD
![ERD.png](images%2FERD.png)

## 주요 기능 흐름도
### 공연 등록 및 조회
![데통설 공연 플로우.png](images%2F%EB%8D%B0%ED%86%B5%EC%84%A4%20%EA%B3%B5%EC%97%B0%20%ED%94%8C%EB%A1%9C%EC%9A%B0.png)

## 주요 API
![API.png](images%2FAPI.png)

## 콘솔 창에서 스프링부트 띄우기 (윈도우 기준)
1. `git clone` 후 프로젝트로 이동 (`cd` 명령어)
2. `dir` 명령어로 폴더 내에 `gradlew` 파일있는지 확인
3. `gradlew build` 명령어로 빌드
  - `gradlew clean build -x test` 명령어로 테스트 코드 실행 없이 빌드
4. `/build/libs` 폴더로 이동해서 빌드된 jar 파일 확인
5. `java -jar `빌드 파일 이름.jar`로 스프링부트 실행
