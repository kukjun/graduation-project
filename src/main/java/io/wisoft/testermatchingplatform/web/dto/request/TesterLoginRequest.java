package io.wisoft.testermatchingplatform.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TesterLoginRequest {

    private String email;
    private String password;
    private String nickName;
    private String phoneNumber;
    private String preferCategoryName;
    private String introMessage;
    private MultipartFile profileImage;

}
