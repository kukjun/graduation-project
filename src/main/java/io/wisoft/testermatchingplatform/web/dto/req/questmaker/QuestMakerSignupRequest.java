package io.wisoft.testermatchingplatform.web.dto.req.questmaker;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestMakerSignupRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    @NotBlank
    @Size(min = 2,max = 10)
    private String nickname;
    @NotBlank
    private String phoneNumber;
}
