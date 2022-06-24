package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.service.quest.QuestChoiceService;
import io.wisoft.testermatchingplatform.web.dto.res.SummarizedQuestResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestController {

    private final QuestChoiceService questChoiceService;

//    @GetMapping("/quests")
//    public List<SummarizedQuestResponseDto> findAllQuest() {
//        return questChoiceService.findAllQuest();
//    }

}
