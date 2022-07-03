package io.wisoft.testermatchingplatform.web.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TesterRegisterRequest {
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String preferCategoryName;
    private String introMessage;
    private String introPictureReference;
}
