package io.wisoft.testermatchingplatform.web.dto.request;


import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class QuestMakerRegisterRequest {
    private final String email;
    private final String password;
    private final String nickname;
    private final String phoneNumber;

    public QuestMakerEntity toEntity() {
        return new QuestMakerEntity(
                getEmail(),
                getPassword(),
                getNickname(),
                getPhoneNumber()
        );
    }
}
