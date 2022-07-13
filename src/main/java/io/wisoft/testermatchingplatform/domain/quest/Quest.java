package io.wisoft.testermatchingplatform.domain.quest;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMaker;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Quest {
    private Long id;
    private String title;
    private String content;
    private Category category;
    private Timestamp registerTime;
    private Timestamp recruitmentTimeStart;
    private Timestamp recruitmentTimeLimit;
    private Timestamp durationTimeStart;
    private Timestamp durationTimeLimit;
    private Timestamp modifyTimeStart;
    private Timestamp modifyTimeLimit;
    private QuestMaker questMaker;
    private int participantCapacity;
    private int reward;
    private String requireCondition;
    private String preferenceCondition;

    public Quest(String title, String content, Category category, Timestamp registerTime, Timestamp recruitmentTimeStart, Timestamp recruitmentTimeLimit, Timestamp durationTimeStart, Timestamp durationTimeLimit, Timestamp modifyTimeStart, Timestamp modifyTimeLimit, QuestMaker questMaker, int participantCapacity, int reward, String requireCondition, String preferenceCondition) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.registerTime = registerTime;
        this.recruitmentTimeStart = recruitmentTimeStart;
        this.recruitmentTimeLimit = recruitmentTimeLimit;
        this.durationTimeStart = durationTimeStart;
        this.durationTimeLimit = durationTimeLimit;
        this.modifyTimeStart = modifyTimeStart;
        this.modifyTimeLimit = modifyTimeLimit;
        this.questMaker = questMaker;
        this.participantCapacity = participantCapacity;
        this.reward = reward;
        this.requireCondition = requireCondition;
        this.preferenceCondition = preferenceCondition;
    }
}
