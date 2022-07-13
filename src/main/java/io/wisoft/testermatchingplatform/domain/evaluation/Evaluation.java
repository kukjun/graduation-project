package io.wisoft.testermatchingplatform.domain.evaluation;

import io.wisoft.testermatchingplatform.domain.auth.Auth;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Evaluation {

    private Auth authId;
    private Long starPoint;
    private String comment;
}
