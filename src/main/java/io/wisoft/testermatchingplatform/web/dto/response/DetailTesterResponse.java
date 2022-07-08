package io.wisoft.testermatchingplatform.web.dto.response;

import io.wisoft.testermatchingplatform.domain.tester.Tester;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailTesterResponse {

    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String preferCategoryName;
    private String introMessage;
    private String introPictureReference;

    public DetailTesterResponse(Tester tester) {
        this.email = tester.getEmail();
        this.password = tester.getPassword();
        this.nickname = tester.getNickname();
        this.phoneNumber = tester.getPhoneNumber();
        this.preferCategoryName = tester.getPreferCategory().getName();
        this.introMessage = tester.getIntroMessage();
        this.introPictureReference = tester.getIntroPictureReference();
    }



}
