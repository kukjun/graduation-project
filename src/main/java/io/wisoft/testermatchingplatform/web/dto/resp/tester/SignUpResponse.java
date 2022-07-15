package io.wisoft.testermatchingplatform.web.dto.resp.tester;

import lombok.Getter;

@Getter
public class SignUpResponse {
    private Long id;
    public SignUpResponse(Long id) {
        this.id = id;
    }
}
