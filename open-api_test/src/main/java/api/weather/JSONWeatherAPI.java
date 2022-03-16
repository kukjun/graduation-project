package main.java.api.weather;

import main.java.vo.Weather;

public class JSONWeatherAPI implements WeatherAPI {

    // WeatherValue 값들로 존재하는 enum 생성
    enum WeatherValue {
        PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
    }

    // 해당하는 api 가 필요로 하는 변수
    private String apiURL;
    private String authKey;


    public JSONWeatherAPI(String apiURL, String authKey) {
        this.apiURL = apiURL;
        this.authKey = authKey;
    }

    @Override
    public Weather use(String x, String y, String baseDate, String baseTime) throws Exception {
        return null;
    }
}
