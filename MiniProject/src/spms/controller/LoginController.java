package spms.controller;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {

    MemberDao memberDao;

    public LoginController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception{
        Member member = (Member) model.get("member");

        // 맵 객체가 없으면 로그인 URL 반환 - 로그인 실행 시, loginForm 을 제공하지 않고 바로
        if (member.getId() == null) {
            return "/login/LoginForm.jsp";
        }

        // 존재하면 맵 객체로부터 Dao, member, Session 객체를 가져와서 맴버가 존재하는지 확인한 뒤에 Session 에 보관
        // 회원 목록으로 리다이렉트 하는 URL 을 반환하거나 로그인 실패 URL을 반환
        else {
            int memberNo = memberDao.exist(member.getId(), member.getPassword());
            if (memberNo != 0) {
                ((HttpSession) model.get("session"))
                        .setAttribute("memberNo", memberNo);
                return "redirect:/api/ChoiceAPI.jsp";
            } else {
                return "/login/LoginFail.jsp";
            }
        }
    }

    @Override
    public Object[] getDataBinders() {
        return new Object[]{
                "member", spms.vo.Member.class
        };
    }

}