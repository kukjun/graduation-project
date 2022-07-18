package io.wisoft.testermatchingplatform.web.controller.quest;

import io.wisoft.testermatchingplatform.service.quest.QuestFoundService;
import io.wisoft.testermatchingplatform.web.dto.resp.quest.QuestInfoResponse;
import io.wisoft.testermatchingplatform.web.dto.resp.quest.QuestSimpleInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quests")
public class QuestFoundController {

    private final QuestFoundService questFoundService;

    @GetMapping("")
    public ResponseEntity<List<QuestSimpleInfoResponse>> questAll(){
        List<QuestSimpleInfoResponse> responseList = questFoundService.all();
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/category")
    public ResponseEntity<List<QuestSimpleInfoResponse>> questFindByCategoryId(@RequestParam final Long category_id){
        List<QuestSimpleInfoResponse> responseList = questFoundService.findByCategoryId(category_id);
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping("/quest")
    public ResponseEntity<QuestInfoResponse> questFindById(@RequestParam final Long quest_id){
        QuestInfoResponse response = questFoundService.findById(quest_id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/questMaker")
    public ResponseEntity<List<QuestSimpleInfoResponse>> questFindByQuestMakerId(@RequestParam final Long questMaker_id){
        List<QuestSimpleInfoResponse> response = questFoundService.findByQuestMakerId(questMaker_id);
        return ResponseEntity.ok().body(response);
    }
}
