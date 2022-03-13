package spms.controller;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.LocationDao;
import spms.dao.MemberDao;
import spms.vo.Location;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/api/weather/inputLocalInfo.do")
public class InputLocalController implements Controller, DataBinding {

    MemberDao memberDao;
    LocationDao locationDao;

    public InputLocalController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    public InputLocalController setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Location location = (Location) model.get("location");

        if (location.getLocalLevel1() == null) {
            return "/api/weather/NotFoundLocal.jsp";
        }

        // x, y 값을 통해서 db에 저장된 값 가져오기

        int findLocationCode = locationDao.findLocationCode(location.getLocalLevel1(), location.getLocalLevel2(), location.getLocalLevel3());

        // session 에 있는 사용자를 가져와서 해당 사용자의 locationCode 를 현재 등록한 location 으로 업데이트함.
        int memberNo = (int) ((HttpSession) model.get("session")).getAttribute("memberNo");
        Member member = memberDao.selectOne(memberNo);
        member.setLocationCode(findLocationCode);
        memberDao.update(member);

        return "/api/weather/WeatherMain.jsp";
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "location", spms.vo.Location.class
        };
    }

}