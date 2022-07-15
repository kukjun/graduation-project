package io.wisoft.testermatchingplatform.web.dto.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TesterUpdateResponse {
    Long id;

    public TesterUpdateResponse(Long id) {
        this.id = id;
    }
}
