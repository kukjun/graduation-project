package io.wisoft.testermatchingplatform.service.quest;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;
import io.wisoft.testermatchingplatform.web.dto.response.SummarizedQuestResponseDto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QuestListReadServiceTest {

    @InjectMocks
    QuestListReadService questListReadService;

    @Mock
    QuestRepository questRepository;

    @Test
    public void QuestList_조회() {
        //given
        Optional<QuestEntity> quest1 = Optional.of(new QuestEntity(
                "title",
                "content",
                new CategoryEntity("게임 테스트"),
                new QuestMakerEntity(
                        "kukjunQuestMaker@naver.com",
                        "12341234",
                        "나는킹준",
                        "010-2345-6463"
                ),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis() + 20000),
                new Timestamp(System.currentTimeMillis() + 40000),
                new Timestamp(System.currentTimeMillis() + 60000),
                new Timestamp(System.currentTimeMillis() + 80000),
                new Timestamp(System.currentTimeMillis() + 100000),
                100L,
                5000L
        ));
        Optional<QuestEntity> quest2 = Optional.of(new QuestEntity(
                "title2",
                "content2",
                new CategoryEntity("웹 기능 테스트"),
                new QuestMakerEntity(
                        "heeyoungQuestMaker@naver.com",
                        "12341234",
                        "나는킹영",
                        "010-4321-6463"
                ),
                new Timestamp(System.currentTimeMillis() + 10000),
                new Timestamp(System.currentTimeMillis() + 30000),
                new Timestamp(System.currentTimeMillis() + 50000),
                new Timestamp(System.currentTimeMillis() + 70000),
                new Timestamp(System.currentTimeMillis() + 90000),
                new Timestamp(System.currentTimeMillis() + 110000),
                1000L,
                500L
        ));

        List<Optional<QuestEntity>> list = new ArrayList<>();
        list.add(quest1);
        list.add(quest2);

        //mocking
        given(questRepository.findAllDesc())
                .willReturn(list);

        //when
        List<SummarizedQuestResponseDto> responseList = questListReadService.findAllQuest();

        //then
        SummarizedQuestResponseDto summarizedQuestResponseDto1 = responseList.get(0);
        SummarizedQuestResponseDto summarizedQuestResponseDto2 = responseList.get(1);
        Quest questDomain1 = list.get(0).get().toDomain();
        Quest questDomain2 = list.get(1).get().toDomain();

        assertEquals(questDomain1.getTitle(), summarizedQuestResponseDto1.getTitle());
        assertEquals(questDomain2.getTitle(), summarizedQuestResponseDto2.getTitle());
        // 작성 진행중


    }

}