package io.wisoft.testermatchingplatform.service.register;

import io.wisoft.testermatchingplatform.domain.ntc.NTC;
import io.wisoft.testermatchingplatform.domain.ntc.NTCEntity;
import io.wisoft.testermatchingplatform.domain.ntc.NTCRepository;
import io.wisoft.testermatchingplatform.handler.exception.EmailOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.NicknameOverlapException;
import io.wisoft.testermatchingplatform.web.dto.request.NTCRegisterRequest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NTCRegisterServiceTest {

    @InjectMocks
    NTCRegisterService ntcRegisterService;

    @Mock
    NTCRepository ntcRepository;

    @Test
    public void ntc_회원_등록() {
        //given
        NTCRegisterRequest request = createNTCRequest();
        NTCEntity ntcEntity = createNTCEntity(request);

        Long fakeNTCId = 1L;
        ReflectionTestUtils.setField(ntcEntity, "id", fakeNTCId);

        //mocking
        given(ntcRepository.save(any()))
                .willReturn(ntcEntity);
        given(ntcRepository.findById(fakeNTCId))
                .willReturn(Optional.ofNullable(ntcEntity));


        //when
        Long newNTCId = ntcRegisterService.registerNTC(request);

        //then
        NTCEntity findNTCEntity = ntcRepository.findById(newNTCId).get();

        NTC ntc = ntcEntity.toDomain();
        NTC findNTC = findNTCEntity.toDomain();


        assertEquals(ntc.getId(), findNTC.getId());
        assertEquals(ntc.getPassword(), findNTC.getPassword());
        assertEquals(ntc.getEmail(), findNTC.getEmail());
        assertEquals(ntc.getPhoneNumber(), findNTC.getPhoneNumber());

    }

//    @Test
//    public void ntc_중복_이메일회원_예외발생() {
//        //given
//        NTCRegisterRequest request = createNTCRequest();
//        NTCRegisterRequest overlapRequest = createNTCRequest();
//        NTCEntity ntcEntity = createNTCEntity(request);
//        NTCEntity overlapNTCEntity = createNTCEntity(overlapRequest);
//
//        Long fakeNTCId = 1L;
//        ReflectionTestUtils.setField(ntcEntity, "id", fakeNTCId);
//
//        // mocking
//        given(ntcRepository.save(any()))
//                .willReturn(ntcEntity);
//        given(ntcRepository.findById(fakeNTCId))
//                .willReturn(Optional.ofNullable(ntcEntity));
//        Long newNTCId = ntcRegisterService.registerNTC(request);
//
//
//        //when
//        ntcRegisterService.registerNTC(overlapRequest);
//
//
//    }

    private NTCEntity createNTCEntity(NTCRegisterRequest request) {
        return new NTCEntity(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getPhoneNumber()
        );
    }

    private NTCRegisterRequest createNTCRequest() {
        return new NTCRegisterRequest(
                "kukjun@naver.com", "12341234", "imKing", "010-1234-5678"
        );
    }

    private NTCRegisterRequest createOverlapEmailNTCRequest() {
        return new NTCRegisterRequest(
                "kukjun@naver.com", "123414321", "ImJun", "010-1234-4241"
        );
    }

    private NTCRegisterRequest createOverlapNicknameNTCRequest() {
        return new NTCRegisterRequest(
                "kukjun@naver.com", "12341234", "imKing", "010-1234-5678"
        );
    }

}