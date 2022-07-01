package io.wisoft.testermatchingplatform.service.register;

import io.wisoft.testermatchingplatform.domain.ntc.NTCRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NTCRegisterServiceTest {

    @InjectMocks
    NTCRegisterService ntcRegisterService;

    @Mock
    NTCRepository ntcRepository;

    public void ntc_회원_등록() {
        //given

        //when

        //then

    }

}