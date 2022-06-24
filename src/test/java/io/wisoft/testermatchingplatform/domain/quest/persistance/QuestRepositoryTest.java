package io.wisoft.testermatchingplatform.domain.quest.persistance;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
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

    @AfterEach
    public void cleanup() {
        questRepository.deleteAll();
    }

    @Test
    public void 퀘스트_전체_불러오기() {
        // given
        String title = "testTitle";
        String content = "testContent";
        String categoryName = "게임 테스트";
        Long ntcId = 1L;
        Timestamp registerTime = new Timestamp(System.currentTimeMillis());
        Timestamp recruitmentTimeStart = new Timestamp(System.currentTimeMillis() + 20000);
        Timestamp recruitmentTimeLimit = new Timestamp(System.currentTimeMillis() + 40000);
        Timestamp durationTimeStart = new Timestamp(System.currentTimeMillis() + 60000);
        Timestamp durationTimeLimit = new Timestamp(System.currentTimeMillis() + 80000);
        Timestamp modifyTimeStart = new Timestamp(System.currentTimeMillis() + 100000);
        Timestamp modifyTimeLimit = new Timestamp(System.currentTimeMillis() + 120000);
        Long capacity = 1L;
        Long paymentPoint = 1000L;

        CategoryEntity categoryEntity = new CategoryEntity(categoryName);

        categoryRepository.save(categoryEntity);

        questRepository.save(new QuestEntity(
                title, content, categoryEntity, ntcId, registerTime, recruitmentTimeStart, recruitmentTimeLimit, durationTimeStart, durationTimeLimit, modifyTimeStart, modifyTimeLimit, capacity, paymentPoint
        ));

        // when
        List<QuestEntity> entityList = questRepository.findAllDesc();
        Quest quest = entityList.get(0).toDomain();

        // then
        assertEquals(title, quest.getTitle());
        assertEquals(content, quest.getContent());
        assertEquals(categoryName, quest.getCategory().getName());
        assertEquals(ntcId, quest.getNtcId());
        assertEquals(registerTime, quest.getRegisterTime());

    }

    @Test
    public void 카테고리_분류된_퀘스트_불러오기() {
        //given
        String title1 = "testTitle";
        String content1 = "testContent";
        Long categoryId1 = 1L;
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

        String title2 = "testTitle2";
        String content2 = "testContent2";
        Long categoryId2 = 2L;
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

//        questRepository.save(new QuestEntity(
//                title1, content1, categoryId1, ntcId1, registerTime1, recruitmentTimeStart1, recruitmentTimeLimit1, durationTimeStart1, durationTimeLimit1, modifyTimeStart1, modifyTimeLimit1, capacity1, paymentPoint1
//        ));
//        questRepository.save(new QuestEntity(
//                title2, content2, categoryId2, ntcId2, registerTime2, recruitmentTimeStart2, recruitmentTimeLimit2, durationTimeStart2, durationTimeLimit2, modifyTimeStart2, modifyTimeLimit2, capacity2, paymentPoint2
//        ));
//
//        // when
//        List<QuestEntity> entityList = questRepository.findByCategoryId(categoryId1);
//        Quest quest = entityList.get(0).toDomain();
//
//        // then
//        assertEquals(1, entityList.size());
//        assertEquals(title1, quest.getTitle());
//        assertEquals(content1, quest.getContent());
//        assertEquals(capacity1, quest.getCategory());
//        assertEquals(ntcId1, quest.getNtcId());
//        assertEquals(registerTime1, quest.getRegisterTime());


    }

}