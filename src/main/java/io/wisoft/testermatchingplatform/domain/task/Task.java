package io.wisoft.testermatchingplatform.domain.task;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Task {

    private Long id;
    private Long taskNumber;
    private Quest quest;
    private String taskExampleRef;
    private String require;
}
