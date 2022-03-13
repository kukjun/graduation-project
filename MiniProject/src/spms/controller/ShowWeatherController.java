package spms.controller;

import spms.annotation.Component;
import spms.api.weather.WeatherAPI;
import spms.bind.DataBinding;
import spms.dao.LocationDao;
import spms.dao.MemberDao;
import spms.vo.Location;
import spms.vo.Member;
import spms.vo.Weather;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/api/weather/showWeatherInfo.do")
public class ShowWeatherController implements Controller, DataBinding {
    MemberDao memberDao;
    LocationDao locationDao;
    WeatherAPI api = new WeatherAPI(
            "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst",
            "Tk3dN%2FBOZoQkVmZXXo3ZQSKXHvJL4SwU%2FFV8rcF%2BsMsEXFfRuSdcPl6oweXAbVNKD3TiJpBFxBop76XQb45ZFg%3D%3D"
    );
    String baseDate = "20220313";
    String baseTime = "1800";

    public ShowWeatherController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    public ShowWeatherController setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        // session 에 저장된 memberNo 가져오기
        int memberNo = (int) ((HttpSession) model.get("session")).getAttribute("memberNo");

        // 로그인을 안하고 실행되는 비정상적인 요청
        if (memberNo == 0) {
            return "/api/weather/Error.jsp";
        }

        // member 가져오기
        Member member = memberDao.selectOne(memberNo);

        // 이전에 지역을 등록 안한 경우
        if (member.getLocationCode() == 0) {
            return "/api/weather/NotFoundLocal.jsp";
        }

        // 이전에 지역 등록을 한 경우
        else {
            Location location = locationDao.selectOne(member.getLocationCode());
            Weather weather = api.use(location.getX(), location.getY(), baseDate, baseTime);


            model.put("weather", weather);
            model.put("baseDate", baseDate);
            model.put("baseTime", baseTime);
            return "/api/weather/ShowWeatherInfo.jsp";
        }


    }

    @Override
    public Object[] getDataBinders() {
        return new Object[0];
    }
}
