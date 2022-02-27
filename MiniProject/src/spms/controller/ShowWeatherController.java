package spms.controller;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.LocationDao;
import spms.dao.MemberDao;
import spms.vo.Location;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/api/weather/showWeatherInfo.do")
public class ShowWeatherController implements Controller, DataBinding {
    MemberDao memberDao;
    LocationDao locationDao;

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

            model.put("location", location);
            return "/api/weather/ShowWeatherInfo.jsp";
        }


    }

    @Override
    public Object[] getDataBinders() {
        return new Object[0];
    }
}
