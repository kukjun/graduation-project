package io.wisoft.testermatchingplatform.domain.quest.application;

import io.wisoft.testermatchingplatform.domain.quest.Category;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.quest.persistance.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.quest.persistance.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.persistance.QuestRepository;
import io.wisoft.testermatchingplatform.domain.quest.web.dto.res.SummarizedQuestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestChoiceService {

    private final QuestRepository questRepository;
    private final CategoryRepository categoryRepository;

    // 추후에 수정하기, 조인을 이용해서 한번 조회해볼 수 있도록 함.
    @Transactional(readOnly = true)
    public List<SummarizedQuestResponseDto> findAllQuest() {
        List<Quest> quests = questRepository.findAllDesc().stream().map(QuestEntity::toDomain).collect(Collectors.toList());

        List<Category> categories = new ArrayList<>();

        for (Quest quest : quests) {

            categories.add(categoryRepository.findById(quest.getId()).get().toDomain());
        }

        List<SummarizedQuestResponseDto> resultList = new ArrayList<>();
        for (int i = 0; i < quests.size(); i++) {
            resultList.add(new SummarizedQuestResponseDto(quests.get(i), categories.get(i)));
        }

        return resultList;


    }


}
