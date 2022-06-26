package io.wisoft.testermatchingplatform.domain.quest.web;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.service.quest.QuestListReadService;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.web.dto.res.SummarizedQuestResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


// Jpa 연동을 통해 Controller 동작 뿐 아니라 데이터가 잘 입력되었는지 까지 확인함.
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestListReadControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private QuestListReadService questListReadService;

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


    @AfterEach
    public void clear() {
        // 연관관계 중요, categoryRepository 먼저 지우면 안됨!!
        questRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void Quest_전부_조회하기() throws Exception {

        //given
        String title1 = "testTitle";
        String content1 = "testContent";
        Long ntcId1 = 1L;
        Timestamp registerTime1 = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart1 = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit1 = new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart1 = new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit1 = new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart1 = new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit1 = new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity1 = 1L;
        Long paymentPoint1 = 1000L;

        String categoryName1 = "게임 테스트";

        CategoryEntity categoryEntity1 = new CategoryEntity(categoryName1);

        categoryRepository.save(categoryEntity1);
        questRepository.save(new QuestEntity(
                title1, content1, categoryEntity1, ntcId1, registerTime1, recruitmentTimeStart1, recruitmentTimeLimit1, durationTimeStart1, durationTimeLimit1, modifyTimeStart1, modifyTimeLimit1, capacity1, paymentPoint1
        ));


        String url = "http://localhost:" + port + "/quests";

        // when
        mvc.perform(get(url))
                .andExpect(status().isOk());

        // then

        List<SummarizedQuestResponseDto> dtoList = questListReadService.findAllQuest();
        SummarizedQuestResponseDto result = dtoList.get(0);

        assertEquals(1L, result.getId());
        assertEquals(title1, result.getTitle());
        assertEquals(categoryName1, result.getCategoryName());
        assertEquals(registerTime1, result.getRegisterTime());
        assertEquals(recruitmentTimeLimit1, result.getRecruitmentTimeLimit());
        assertEquals(durationTimeStart1, result.getDurationTimeStart());
        assertEquals(durationTimeLimit1, result.getDurationTimeLimit());
        assertEquals(capacity1, result.getCapacity());
        assertEquals(paymentPoint1, result.getPaymentPoint());

    }


    @Test
    public void Quest_카테고리로_조회하기() throws Exception {

        //given
        String title1 = "testTitle";
        String content1 = "testContent";
        Long ntcId1 = 1L;
        Timestamp registerTime1 = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart1 = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit1 = new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart1 = new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit1 = new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart1 = new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit1 = new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity1 = 1L;
        Long paymentPoint1 = 1000L;

        String categoryName1 = "게임 테스트";

        CategoryEntity categoryEntity1 = new CategoryEntity(categoryName1);

        categoryRepository.save(categoryEntity1);
        questRepository.save(new QuestEntity(
                title1, content1, categoryEntity1, ntcId1, registerTime1, recruitmentTimeStart1, recruitmentTimeLimit1, durationTimeStart1, durationTimeLimit1, modifyTimeStart1, modifyTimeLimit1, capacity1, paymentPoint1
        ));


        String title2 = "testTitle2";
        String content2 = "testContent2";
        Long ntcId2 = 2L;
        Timestamp registerTime2 = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart2 = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit2 = new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart2 = new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit2 = new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart2 = new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit2 = new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity2 = 2L;
        Long paymentPoint2 = 2000L;

        String categoryName2 = "기능 테스트";

        CategoryEntity categoryEntity2 = new CategoryEntity(categoryName2);

        categoryRepository.save(categoryEntity2);
        questRepository.save(new QuestEntity(
                title2, content2, categoryEntity2, ntcId2, registerTime2, recruitmentTimeStart2, recruitmentTimeLimit2, durationTimeStart2, durationTimeLimit2, modifyTimeStart2, modifyTimeLimit2, capacity2, paymentPoint2
        ));

        String url = "http://localhost:" + port + "/quests/" + categoryName1;

        // when
        mvc.perform(
                get(url)
        ).andExpect(status().isOk());

        // then
        List<SummarizedQuestResponseDto> dtoList = questListReadService.findToCategoryName(categoryName1);
        SummarizedQuestResponseDto result = dtoList.get(0);

        assertEquals(categoryEntity1.toDomain().getId(), result.getId());
        assertEquals(title1, result.getTitle());
        assertEquals(categoryName1, result.getCategoryName());
        assertEquals(registerTime1, result.getRegisterTime());
        assertEquals(recruitmentTimeLimit1, result.getRecruitmentTimeLimit());
        assertEquals(durationTimeStart1, result.getDurationTimeStart());
        assertEquals(durationTimeLimit1, result.getDurationTimeLimit());
        assertEquals(capacity1, result.getCapacity());
        assertEquals(paymentPoint1, result.getPaymentPoint());


    }

}