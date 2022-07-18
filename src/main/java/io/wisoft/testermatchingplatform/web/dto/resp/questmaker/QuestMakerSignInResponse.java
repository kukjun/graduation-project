package io.wisoft.testermatchingplatform.web.dto.resp.questmaker;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestMakerSignInResponse {

    private Long id;

    public static QuestMakerSignInResponse from(final Long id) {
        return new QuestMakerSignInResponse(id);
    }
}
