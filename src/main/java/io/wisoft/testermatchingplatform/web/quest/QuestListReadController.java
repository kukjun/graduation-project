package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.service.quest.QuestListReadService;
import io.wisoft.testermatchingplatform.web.dto.res.SummarizedQuestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestListReadController {

    private final QuestListReadService questListReadService;

    @GetMapping("/quests")
    public List<SummarizedQuestResponseDto> findAllQuest() {
        return questListReadService.findAllQuest();
    }

    @GetMapping("/quests/{categoryName}")
    public List<SummarizedQuestResponseDto> findToCategoryName(
            @PathVariable String categoryName
    ) {
        return questListReadService.findToCategoryName(categoryName);
    }

}
