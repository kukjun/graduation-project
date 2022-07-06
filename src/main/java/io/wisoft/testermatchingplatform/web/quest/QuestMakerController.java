package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.service.register.QuestMakerRegisterService;
import io.wisoft.testermatchingplatform.web.dto.request.QuestMakerRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestMakerController {

    final QuestMakerRegisterService questMakerRegisterService;

    @Autowired
    public QuestMakerController(QuestMakerRegisterService questMakerRegisterService) {
        this.questMakerRegisterService = questMakerRegisterService;
    }

    @PostMapping("/ntcs")
    public Long registerNTC(@RequestBody QuestMakerRegisterRequest questMakerRegisterRequest) {
        return questMakerRegisterService.registerNTC(questMakerRegisterRequest);
    }
}
