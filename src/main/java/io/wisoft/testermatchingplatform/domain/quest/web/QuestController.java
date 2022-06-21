package io.wisoft.testermatchingplatform.domain.quest.web;

import io.wisoft.testermatchingplatform.domain.quest.application.QuestChoiceService;
import io.wisoft.testermatchingplatform.domain.quest.web.dto.res.SummarizedQuestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestController {

    private final QuestChoiceService questChoiceService;

    @GetMapping("/quests")
    public List<SummarizedQuestResponseDto> findAllQuest() {
        return questChoiceService.findAllQuest();
    }

}
