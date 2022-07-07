package io.wisoft.testermatchingplatform.web.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class DetailTesterResponse {

    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String preferCategoryName;
    private String introMessage;
    private String introPictureRef;

}
