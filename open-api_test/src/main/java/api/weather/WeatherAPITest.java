package main.java.api.weather;

import main.java.vo.Weather;

public class WeatherAPITest{
    public static void main(String[] args) {
        XMLWeatherAPI api = new XMLWeatherAPI(
                "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst",
                "Tk3dN%2FBOZoQkVmZXXo3ZQSKXHvJL4SwU%2FFV8rcF%2BsMsEXFfRuSdcPl6oweXAbVNKD3TiJpBFxBop76XQb45ZFg%3D%3D"
        );

        try {
            Weather weather = api.use("69", "100", "20220310", "1800");
            System.out.println(weather.toString());

        } catch (Exception e) {

        }

    }
}
