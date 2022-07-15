package io.wisoft.testermatchingplatform.web.dto.req.tester;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class TesterSignInRequest {

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

}
