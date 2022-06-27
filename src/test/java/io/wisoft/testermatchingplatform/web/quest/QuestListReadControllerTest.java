package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.ntc.NTCEntity;
import io.wisoft.testermatchingplatform.domain.ntc.NTCRepository;
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
    private NTCRepository ntcRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
    @BeforeEach
    public void setupData() {
        //given
        String categoryName1 = "게임 테스트";
        CategoryEntity categoryEntity1 = new CategoryEntity(categoryName1);
        categoryRepository.save(categoryEntity1);


        String ntcEmail1 = "kukjun@test.com";
        String ntcNickname1 = "킹준";
        String ntcPhoneNumber1 = "010-1234-8359";
        NTCEntity ntcEntity1 = new NTCEntity(ntcEmail1, ntcNickname1, ntcPhoneNumber1);
        ntcRepository.save(ntcEntity1);

        String title1 = "testTitle";
        String content1 = "testContent";
        Timestamp registerTime1 = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart1 = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit1 = new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart1 = new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit1 = new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart1 = new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit1 = new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity1 = 1L;
        Long paymentPoint1 = 1000L;
        questRepository.save(new QuestEntity(
                        title1,
                        content1,
                        categoryEntity1,
                        ntcEntity1,
                        registerTime1,
                        recruitmentTimeStart1,
                        recruitmentTimeLimit1,
                        durationTimeStart1,
                        durationTimeLimit1,
                        modifyTimeStart1,
                        modifyTimeLimit1,
                        capacity1,
                        paymentPoint1
                )
        );

        String categoryName2 = "기능 테스트";
        CategoryEntity categoryEntity2 = new CategoryEntity(categoryName2);
        categoryRepository.save(categoryEntity2);

        String ntcEmail2 = "heyoung@test.com";
        String ntcNickname2 = "킹희영";
        String ntcPhoneNumber2 = "010-1234-5678";
        NTCEntity ntcEntity2 = new NTCEntity(ntcEmail2, ntcNickname2, ntcPhoneNumber2);
        ntcRepository.save(ntcEntity2);

        String title2 = "testTitle2";
        String content2 = "testContent2";
        Timestamp registerTime2 = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart2 = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit2 = new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart2 = new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit2 = new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart2 = new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit2 = new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity2 = 2L;
        Long paymentPoint2 = 2000L;


        questRepository.save(new QuestEntity(
                        title2,
                        content2,
                        categoryEntity2,
                        ntcEntity2,
                        registerTime2,
                        recruitmentTimeStart2,
                        recruitmentTimeLimit2,
                        durationTimeStart2,
                        durationTimeLimit2,
                        modifyTimeStart2,
                        modifyTimeLimit2,
                        capacity2,
                        paymentPoint2
                )
        );
    }



    @AfterEach
    public void clear() {
        // 연관관계 중요, categoryRepository 먼저 지우면 안됨!!
        questRepository.deleteAll();
        categoryRepository.deleteAll();
        ntcRepository.deleteAll();
    }

    @Test
    public void Quest_전부_조회하기() throws Exception {

        //given
        String title1 = "testTitle2";
        Long capacity1 = 2L;
        Long paymentPoint1 = 2000L;
        String categoryName1 = "기능 테스트";


        String url = "http://localhost:" + port + "/quests";

        // when
        mvc.perform(get(url))
                .andExpect(status().isOk());

        // then
        List<SummarizedQuestResponseDto> dtoList = questListReadService.findAllQuest();
        SummarizedQuestResponseDto result = dtoList.get(0);

        assertEquals(2, dtoList.size());
        assertEquals(2L, result.getId());
        assertEquals(title1, result.getTitle());
        assertEquals(categoryName1, result.getCategoryName());
        assertEquals(capacity1, result.getCapacity());
        assertEquals(paymentPoint1, result.getPaymentPoint());

    }


    @Test
    public void Quest_카테고리로_조회하기() throws Exception {

        //given
        String title1 = "testTitle";
        Long capacity1 = 1L;
        Long paymentPoint1 = 1000L;

        String categoryName1 = "게임 테스트";

        String url = "http://localhost:" + port + "/quests/" + categoryName1;

        // when
        mvc.perform(
                get(url)
        ).andExpect(status().isOk());

        // then
        List<SummarizedQuestResponseDto> dtoList = questListReadService.findToCategoryName(categoryName1);
        SummarizedQuestResponseDto result = dtoList.get(0);

        assertEquals(title1, result.getTitle());
        assertEquals(categoryName1, result.getCategoryName());
        assertEquals(capacity1, result.getCapacity());
        assertEquals(paymentPoint1, result.getPaymentPoint());


    }

}