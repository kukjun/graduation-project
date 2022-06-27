package io.wisoft.testermatchingplatform.service.quest;

import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.web.dto.res.SummarizedQuestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestListReadService {

    private final QuestRepository questRepository;

    @Transactional(readOnly = true)
    public List<SummarizedQuestResponseDto> findAllQuest() {

        return questRepository.findAllDesc().stream()
                .map(QuestEntity::toDomain)
                .map(SummarizedQuestResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<SummarizedQuestResponseDto> findToCategoryName(String categoryName) {

        return questRepository.findByCategoryName(categoryName).stream()
                .map(QuestEntity::toDomain)
                .map(SummarizedQuestResponseDto::new)
                .collect(Collectors.toList());

    }
}
