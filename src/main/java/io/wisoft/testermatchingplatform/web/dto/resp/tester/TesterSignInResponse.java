package io.wisoft.testermatchingplatform.web.dto.resp.tester;

import lombok.Getter;

@Getter
public class TesterSignInResponse {
    private Long id;

    public TesterSignInResponse(Long id) {
        this.id = id;
    }
}
