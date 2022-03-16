package main.java.api.weather;

import main.java.vo.Weather;

public interface WeatherAPI {

    Weather use(String x, String y, String baseDate, String baseTime) throws Exception;

}
