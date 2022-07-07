package io.wisoft.testermatchingplatform.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class TesterUpdateRequest {
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String introMessage;
    private MultipartFile introPictrue;

}
