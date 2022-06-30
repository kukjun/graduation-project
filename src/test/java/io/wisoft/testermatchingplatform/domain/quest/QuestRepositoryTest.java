package io.wisoft.testermatchingplatform.domain.quest;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.ntc.NTCEntity;
import io.wisoft.testermatchingplatform.domain.ntc.NTCRepository;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestRepositoryTest {

    @Autowired
    private QuestRepository questRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NTCRepository ntcRepository;

    @BeforeEach
    public void setupData() {
        //given
        String categoryName1 = "게임 테스트";
        CategoryEntity categoryEntity1 = new CategoryEntity(categoryName1);
        categoryRepository.save(categoryEntity1);


        String ntcEmail1 = "kukjun@test.com";
        String ntcNickname1 = "킹준";
        String ntcPassword1 = "1234";
        String ntcPhoneNumber1 = "010-1234-8359";
        NTCEntity ntcEntity1 = new NTCEntity(ntcEmail1, ntcPassword1, ntcNickname1, ntcPhoneNumber1);
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
        String ntcPassword2 = "4321";
        String ntcPhoneNumber2 = "010-1234-5678";
        NTCEntity ntcEntity2 = new NTCEntity(ntcEmail2, ntcPassword2, ntcNickname2, ntcPhoneNumber2);
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
    public void cleanup() {
        questRepository.deleteAll();
        categoryRepository.deleteAll();
        ntcRepository.deleteAll();
    }

    @Test
    public void 퀘스트_전체_불러오기() {
        // given
        String categoryName1 = "게임 테스트";

        String ntcEmail1 = "kukjun@test.com";

        String title1 = "testTitle";
        String content1 = "testContent";


        // when
        List<QuestEntity> entityList = questRepository.findAllDesc();
        Quest quest = entityList.get(1).toDomain();

        // then
        assertEquals(2, entityList.size());
        assertEquals(title1, quest.getTitle());
        assertEquals(content1, quest.getContent());
        assertEquals(categoryName1, quest.getCategory().getName());
        assertEquals(ntcEmail1, quest.getNtc().getEmail());

    }

    @Test
    public void 카테고리_분류된_퀘스트_불러오기() {
        //given
        String categoryName1 = "게임 테스트";

        String ntcEmail1 = "kukjun@test.com";

        String title1 = "testTitle";
        String content1 = "testContent";


        // when
        List<QuestEntity> entityList = questRepository.findByCategoryName(categoryName1);
        Quest quest = entityList.get(0).toDomain();

        // then
        assertEquals(1, entityList.size());
        assertEquals(title1, quest.getTitle());
        assertEquals(content1, quest.getContent());
        assertEquals(categoryName1, quest.getCategory().getName());
        assertEquals(ntcEmail1, quest.getNtc().getEmail());
    }

}