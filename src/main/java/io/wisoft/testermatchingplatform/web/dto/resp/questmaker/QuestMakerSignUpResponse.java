package io.wisoft.testermatchingplatform.web.dto.resp.questmaker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestMakerSignUpResponse {
    private Long id;

    public static QuestMakerSignUpResponse from(final Long id) {
        return new QuestMakerSignUpResponse(id);
    }
}
