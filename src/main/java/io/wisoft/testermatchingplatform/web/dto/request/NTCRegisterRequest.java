package io.wisoft.testermatchingplatform.web.dto.request;


import io.wisoft.testermatchingplatform.domain.ntc.NTCEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NTCRegisterRequest {
    private final String email;
    private final String password;
    private final String nickname;
    private final String phoneNumber;

    public NTCEntity toEntity() {
        return new NTCEntity(
                getEmail(),
                getPassword(),
                getNickname(),
                getPhoneNumber()
        );
    }
}
