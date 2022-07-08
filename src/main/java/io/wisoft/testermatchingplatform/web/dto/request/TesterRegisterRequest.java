package io.wisoft.testermatchingplatform.web.dto.request;

import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TesterRegisterRequest {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String preferCategoryName;
    private String introMessage;
    private String introPictureReference;

}
