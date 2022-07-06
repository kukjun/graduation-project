package io.wisoft.testermatchingplatform.domain.questmaker;

import lombok.Getter;

@Getter
public class QuestMaker {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;

    public QuestMaker(final Long id,
                      final String email,
                      final String password,
                      final String nickname,
                      final String phoneNumber
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

}
