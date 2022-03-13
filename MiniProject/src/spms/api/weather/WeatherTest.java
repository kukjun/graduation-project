package spms.api.weather;

import spms.vo.Weather;

public class WeatherTest {
    public static void main(String[] args) {
        WeatherAPI api = new WeatherAPI(
                "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst",
                "Tk3dN%2FBOZoQkVmZXXo3ZQSKXHvJL4SwU%2FFV8rcF%2BsMsEXFfRuSdcPl6oweXAbVNKD3TiJpBFxBop76XQb45ZFg%3D%3D"
        );

        try {
            Weather weather = api.use("69", "100", "20220313", "1500");
            System.out.println(weather.toString());

        } catch (Exception e) {

        }

    }
}
