package io.wisoft.testermatchingplatform.service.questmaker;

import io.wisoft.testermatchingplatform.domain.questmaker.QuestMaker;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerRepository;
import io.wisoft.testermatchingplatform.web.dto.req.questmaker.QuestMakerSigninRequest;
import io.wisoft.testermatchingplatform.web.dto.req.questmaker.QuestMakerSignupRequest;
import io.wisoft.testermatchingplatform.web.dto.resp.questmaker.QuestMakerIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class QuestMakerAuthService {
    private final QuestMakerRepository questMakerRepository;
    @Transactional
    public QuestMakerIdResponse signupQuestMaker(final QuestMakerSignupRequest request){
        QuestMaker questMaker = new QuestMaker(
                request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getPhoneNumber(),
                new Timestamp(System.currentTimeMillis())
        );
        questMaker.checkPassword(request.getConfirmPassword());
        questMaker = questMakerRepository.save(QuestMakerEntity.from(questMaker)).toDomain();
        return QuestMakerIdResponse.from(questMaker.getId());
    }

    @Transactional
    public void deleteQuestMaker(final Long questMakerId){
        // 회원 탈퇴
        questMakerRepository.deleteById(questMakerId);
    }

    @Transactional
    public QuestMakerIdResponse loginQuestMaker(final QuestMakerSigninRequest request){

        QuestMaker questMaker = questMakerRepository.findByEmail(request.getEmail()).orElseThrow(
                // 예외 처리
        ).toDomain();

        questMaker.checkPassword(request.getPassword());

        return QuestMakerIdResponse.from(questMaker.getId());
    }
}
