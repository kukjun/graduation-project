package io.wisoft.testermatchingplatform.domain.quest.web;

import io.wisoft.testermatchingplatform.domain.quest.application.QuestChoiceService;
import io.wisoft.testermatchingplatform.domain.quest.persistance.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.quest.persistance.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.quest.persistance.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.persistance.QuestRepository;
import io.wisoft.testermatchingplatform.domain.quest.web.dto.res.SummarizedQuestResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private QuestChoiceService questChoiceService;

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @AfterEach
    public void clear() {
        categoryRepository.deleteAll();
        questRepository.deleteAll();
    }

    @Test
    public void Quest_그냥_조회하기() {

        // given
        String title = "testTitle";
        String content = "testContent";
        Long categoryId = 1L;
        Long ntcId = 1L;
        Timestamp registerTime = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit =  new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart =  new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit =  new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart =  new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit =  new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity = 1L;
        Long paymentPoint = 1000L;

        String categoryName = "게임테스트";

        questRepository.save(new QuestEntity(
                title, content, categoryId, ntcId, registerTime, recruitmentTimeStart, recruitmentTimeLimit, durationTimeStart, durationTimeLimit, modifyTimeStart, modifyTimeLimit, capacity, paymentPoint
        ));
        categoryRepository.save(new CategoryEntity(categoryId, categoryName));

        // when
        List<SummarizedQuestResponseDto> list = questChoiceService.findAllQuest();
        SummarizedQuestResponseDto summarizedQuestResponseDto = list.get(0);

        // then
        assertEquals(1, summarizedQuestResponseDto.getId());
        assertEquals(title, summarizedQuestResponseDto.getTitle());
        assertEquals(categoryName, summarizedQuestResponseDto.getCategoryName());
        assertEquals(registerTime, summarizedQuestResponseDto.getRegisterTime());
        assertEquals(recruitmentTimeLimit, summarizedQuestResponseDto.getRecruitmentTimeLimit());
        assertEquals(durationTimeStart, summarizedQuestResponseDto.getDurationTimeStart());
        assertEquals(durationTimeLimit, summarizedQuestResponseDto.getDurationTimeLimit());
        assertEquals(capacity, summarizedQuestResponseDto.getCapacity());
        assertEquals(paymentPoint, summarizedQuestResponseDto.getPaymentPoint());

    }







}