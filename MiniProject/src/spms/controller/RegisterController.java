package spms.controller;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

import java.util.Map;

@Component("/register/register.do")
public class RegisterController implements Controller, DataBinding {

    MemberDao memberDao;

    public RegisterController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }


    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member) model.get("member");

        // member가 없으면 잘못된 접근 반환
        if (member.getId() == null) {
            return "/Error.jsp";
        }

        // member가 있으면 생성하는 로직 구현
        // 이후 예외에 따라서 절차변경이 추가되어야 함
        else {
            try {
                int success = memberDao.insert(member);
                if (success == 0) {
                    // 중복된 ID인 경우 처리하기
                    return "/Error.jsp";
                } else {
                    return "/main/MainPage.jsp";
                }
            } catch (Exception e) {
                // 생각지 못한 예외 발생
                e.printStackTrace();
                return "/Error.jsp";
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
