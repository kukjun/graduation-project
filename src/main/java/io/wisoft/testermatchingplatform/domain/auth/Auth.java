package io.wisoft.testermatchingplatform.domain.auth;

import io.wisoft.testermatchingplatform.domain.submit.Submit;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Auth {
    private Long id;
    private Submit submit;
    private Long status;
    private String comment;
}
