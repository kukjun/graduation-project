package io.wisoft.testermatchingplatform.web.dto.resp.submit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SubmitTesterListResponse {
    private String tester;
    private int status;
}
