package io.wisoft.testermatchingplatform.web.dto.resp.tester;

import lombok.Getter;

@Getter
public class SignInResponse {
    private Long id;

    public SignInResponse(Long id) {
        this.id = id;
    }
}
