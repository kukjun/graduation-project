package io.wisoft.testermatchingplatform.domain.quest.persistance;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class QuestRepositoryTest {

    @Autowired
    private QuestRepository questRepository;

    @AfterEach
    public void cleanup() {
        questRepository.deleteAll();
    }

    @Test
    // 퀘스트 내용 생성
    public void 퀘스트_전체_불러오기() {
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

        questRepository.save(new QuestEntity(
                title, content, categoryId, ntcId, registerTime, recruitmentTimeStart, recruitmentTimeLimit, durationTimeStart, durationTimeLimit, modifyTimeStart, modifyTimeLimit, capacity, paymentPoint
        ));

        // when
        List<QuestEntity> entityList = questRepository.findAllDesc();
        Quest quest = entityList.get(0).toDomain();

        // then
        assertEquals(title, quest.getTitle());
        assertEquals(content, quest.getContent());
        assertEquals(categoryId, quest.getCategoryId());
        assertEquals(ntcId, quest.getNtcId());
        assertEquals(registerTime, quest.getRegisterTime());


    }

}