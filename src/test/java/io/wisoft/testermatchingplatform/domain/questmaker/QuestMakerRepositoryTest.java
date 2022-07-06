package io.wisoft.testermatchingplatform.domain.questmaker;

import io.wisoft.testermatchingplatform.handler.exception.EmailOverlapException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestMakerRepositoryTest {

    @Autowired
    QuestMakerRepository questMakerRepository;

    @BeforeEach
    public void dataSetup() {
        // given
        String questMakerEmail1 = "kukjun@test.com";
        String questMakerPassword1 = "1234";
        String questMakerNickname1 = "킹준";
        String questMakerPhoneNumber1 = "010-1234-8359";
        QuestMakerEntity questMakerEntity1 = new QuestMakerEntity(questMakerEmail1, questMakerPassword1, questMakerNickname1, questMakerPhoneNumber1);
        questMakerRepository.save(questMakerEntity1);

        String questMakerEmail2 = "heyoung@test.com";
        String questMakerPassword2 = "4321";
        String questMakerNickname2 = "킹희영";
        String questMakerPhoneNumber2 = "010-1234-5678";
        QuestMakerEntity questMakerEntity2 = new QuestMakerEntity(questMakerEmail2, questMakerPassword2, questMakerNickname2, questMakerPhoneNumber2);
        questMakerRepository.save(questMakerEntity2);
    }

    @AfterEach
    public void cleanup() {
        questMakerRepository.deleteAll();
    }

    @Test
    public void questMaker_전체_조회() {
        //given

        // when
        List<QuestMakerEntity> list = questMakerRepository.findAllDesc();

        // then
        assertEquals(2, list.size());

    }

    @Test
    public void questMaker_이메일로_조회() {
        //given
        String questMakerEmail = "kukjun@test.com";
        String questMakerNickname = "킹준";
        String questMakerPhoneNumber = "010-1234-8359";

        // when
        QuestMaker questMaker = questMakerRepository.findByEmail(questMakerEmail).orElseThrow(
                () -> new EmailOverlapException(questMakerEmail + "은 이미 가입된 이메일입니다.")
        ).toDomain();

        // given
        assertEquals(questMakerEmail, questMaker.getEmail());
        assertEquals(questMakerNickname, questMaker.getNickname());
        assertEquals(questMakerPhoneNumber, questMaker.getPhoneNumber());
    }

    @Test
    public void questMaker_닉네임으로_조회() {
        //given
        String questMakerEmail = "kukjun@test.com";
        String questMakerNickname = "킹준";
        String questMakerPhoneNumber = "010-1234-8359";

        // when
        // 예외 처리 추가
        QuestMaker questMaker = questMakerRepository.findByNickname(questMakerNickname).get().toDomain();

        // given
        assertEquals(questMakerEmail, questMaker.getEmail());
        assertEquals(questMakerNickname, questMaker.getNickname());
        assertEquals(questMakerPhoneNumber, questMaker.getPhoneNumber());
    }



}