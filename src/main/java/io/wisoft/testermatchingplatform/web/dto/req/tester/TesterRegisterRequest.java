package io.wisoft.testermatchingplatform.web.dto.req.tester;

import io.wisoft.testermatchingplatform.handler.validator.image.Custom;
import io.wisoft.testermatchingplatform.handler.validator.image.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Getter
@RequiredArgsConstructor
public class TesterRegisterRequest {

    @NotBlank(message = "해당 항목을 필수 항목입니다.")
    private String email;

    @NotBlank(message = "해당 항목을 필수 항목입니다.")
    private String password;

    @NotBlank(message = "해당 항목을 필수 항목입니다.")
    private String nickname;

    @NotBlank(message = "해당 항목을 필수 항목입니다.")
    private String phoneNumber;

    private String preferCategoryName;

    private String introMessage;

    @Image(groups = Custom.class)
    @NotNull(message = "프로필 이미지를 지정해주세요.", groups = Default.class)
    private MultipartFile introPicture;


}
