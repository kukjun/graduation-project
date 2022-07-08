package io.wisoft.testermatchingplatform.service.register;

import io.wisoft.testermatchingplatform.domain.questmaker.QuestMaker;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerRepository;
import io.wisoft.testermatchingplatform.web.dto.request.QuestMakerRegisterRequest;
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
class QuestMakerRegisterServiceTest {

    @InjectMocks
    QuestMakerRegisterService questMakerRegisterService;

    @Mock
    QuestMakerRepository questMakerRepository;

    @Test
    public void questMaker_회원_등록() {
        //given
        QuestMakerRegisterRequest request = createNTCRequest();
        QuestMakerEntity questMakerEntity = createNTCEntity(request);

        Long fakeNTCId = 1L;
        ReflectionTestUtils.setField(questMakerEntity, "id", fakeNTCId);

        //mocking
        given(questMakerRepository.save(any()))
                .willReturn(questMakerEntity);
        given(questMakerRepository.findById(fakeNTCId))
                .willReturn(Optional.ofNullable(questMakerEntity));

        //when
        Long newNTCId = questMakerRegisterService.registerNTC(request);

        //then
        QuestMakerEntity findQuestMakerEntity = questMakerRepository.findById(newNTCId).get();

        QuestMaker questMaker = questMakerEntity.toDomain();
        QuestMaker findQuestMaker = findQuestMakerEntity.toDomain();

        assertEquals(questMaker.getId(), findQuestMaker.getId());
        assertEquals(questMaker.getPassword(), findQuestMaker.getPassword());
        assertEquals(questMaker.getEmail(), findQuestMaker.getEmail());
        assertEquals(questMaker.getPhoneNumber(), findQuestMaker.getPhoneNumber());

    }

//    @Test
//    public void ntc_중복_이메일회원_예외발생() {
//        //given
//        NTCRegisterRequest request = createNTCRequest();
//        NTCRegisterRequest overlapRequest = createOverlapEmailNTCRequest();
//        NTCEntity ntcEntity = createNTCEntity(request);
//
//        Long fakeNTCId = 1L;
//        ReflectionTestUtils.setField(ntcEntity, "id", fakeNTCId);
//
//        // mocking
//        given(ntcRepository.save(any()))
//                .willReturn(ntcEntity);
//        given(ntcRepository.findById(fakeNTCId))
//                .willReturn(Optional.ofNullable(ntcEntity));
//
//        //when
//        Long newNTCId = ntcRegisterService.registerNTC(request);
//        assertThrows(EmailOverlapException.class, () ->
//                ntcRegisterService.registerNTC(overlapRequest)
//        );
//
//    }

    private QuestMakerEntity createNTCEntity(QuestMakerRegisterRequest request) {
        return new QuestMakerEntity(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getPhoneNumber()
        );
    }

    private QuestMakerRegisterRequest createNTCRequest() {
        return new QuestMakerRegisterRequest(
                "kukjun@naver.com", "12341234", "imKing", "010-1234-5678"
        );
    }
//
//    private NTCRegisterRequest createOverlapEmailNTCRequest() {
//        return new NTCRegisterRequest(
//                "kukjun@naver.com", "123414321", "ImJun", "010-1234-4241"
//        );
//    }
//
//    private NTCRegisterRequest createOverlapNicknameNTCRequest() {
//        return new NTCRegisterRequest(
//                "kukjun@naver.com", "12341234", "imKing", "010-1234-5678"
//        );
//    }

}