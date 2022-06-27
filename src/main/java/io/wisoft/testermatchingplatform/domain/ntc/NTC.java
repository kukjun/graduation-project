package io.wisoft.testermatchingplatform.domain.ntc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class NTC {
    private Long id;
    private String email;
    private String nickname;
    private String phoneNumber;

    public NTC(final Long id,
               final String email,
               final String nickname,
               final String phoneNumber
    ) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

}
