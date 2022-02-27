package spms.controller;

import spms.annotation.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Component("/auth/logout.do")
public class LogoutController implements Controller{
    @Override
    public String execute(Map<String, Object> model) throws Exception {
        HttpSession session = (HttpSession) model.get("session");
        session.invalidate();
        return "/main/MainPage.jsp";
    }
}
