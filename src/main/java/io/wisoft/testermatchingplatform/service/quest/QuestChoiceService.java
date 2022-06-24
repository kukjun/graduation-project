package io.wisoft.testermatchingplatform.service.quest;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.web.dto.res.SummarizedQuestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestChoiceService {

    private final QuestRepository questRepository;
    private final CategoryRepository categoryRepository;

    // 추후에 수정하기, 조인을 이용해서 한번 조회해볼 수 있도록 함.
//    @Transactional(readOnly = true)
//    public List<SummarizedQuestResponseDto> findAllQuest() {
//
//
//    }


}
