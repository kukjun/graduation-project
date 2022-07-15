package io.wisoft.testermatchingplatform.web.dto.resp;

import lombok.Getter;

@Getter
public class TesterSignInResponse {
    private Long id;

    public TesterSignInResponse(Long id) {
        this.id = id;
    }
}
