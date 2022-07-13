package io.wisoft.testermatchingplatform.service.quest;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;
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

        List<Optional<QuestEntity>> list = new ArrayList<>();

        Optional<QuestEntity> quest1 = createQuest1();
        Optional<QuestEntity> quest2 = createQuest2();

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
        assertEquals(questDomain1.getCategory().getName(), summarizedQuestResponseDto1.getCategoryName());
        assertEquals(questDomain2.getCategory().getName(), summarizedQuestResponseDto2.getCategoryName());


    }

    @Test
    public void QuestList_CategoryId로_조회() {

        //given
        List<Optional<QuestEntity>> list1 = new ArrayList<>();
        Optional<QuestEntity> quest1 = createQuest1();
        list1.add(quest1);

        List<Optional<QuestEntity>> list2 = new ArrayList<>();
        Optional<QuestEntity> quest2 = createQuest2();
        list2.add(quest2);

        Long fakeCategoryId1 = 1L;

        //mocking
        given(questRepository.findByCategoryId(fakeCategoryId1))
                .willReturn(list1);

        //when
        List<SummarizedQuestResponseDto> responseList = questListReadService.findByCategoryId(fakeCategoryId1);

        //then
        SummarizedQuestResponseDto summarizedQuestResponseDto = responseList.get(0);
        Quest questDomain = list1.get(0).get().toDomain();

        assertEquals(questDomain.getTitle(), summarizedQuestResponseDto.getTitle());
        assertEquals(questDomain.getParticipantCapacity(), summarizedQuestResponseDto.getParticipantCapacity());
        assertEquals(questDomain.getDurationTimeStart(), summarizedQuestResponseDto.getDurationTimeStart());
        assertEquals(questDomain.getDurationTimeLimit(), summarizedQuestResponseDto.getDurationTimeLimit());

    }

    @Test
    public void Quest_QuestId를_통해_세부조회() {
        //given
        Optional<QuestEntity> quest = createQuest1();
        Long fakeQuestId = 1L;

        //mocking
        given(questRepository.findById(fakeQuestId))
                .willReturn(quest);

        //when
        DetailQuestResponseDto detailQuestResponseDto = questListReadService.findById(fakeQuestId);
        Quest questDomain = quest.get().toDomain();

        //then
        assertEquals(questDomain.getQuestMaker().getNickname(), detailQuestResponseDto.getQuestMakerNickname());
        assertEquals(questDomain.getCategory().getName(), detailQuestResponseDto.getCategoryName());
        assertEquals(questDomain.getTitle(), detailQuestResponseDto.getTitle());

    }


    private Optional<QuestEntity> createQuest1() {
        return Optional.of(new QuestEntity(
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
    }

    private Optional<QuestEntity> createQuest2() {
        return Optional.of(new QuestEntity(
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

    }
}