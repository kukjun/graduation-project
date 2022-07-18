package io.wisoft.testermatchingplatform.domain.task;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long taskNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUEST_ID")
    private Quest quest;
    private String taskExampleRef;
    private String require;

    public Task(Long taskNumber, Quest quest, String taskExampleRef, String require) {
        this.taskNumber = taskNumber;
        this.quest = quest;
        this.taskExampleRef = taskExampleRef;
        this.require = require;
    }
}
