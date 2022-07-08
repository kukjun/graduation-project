package io.wisoft.testermatchingplatform.domain.quest;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMaker;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Quest {

    private Long id;
    private String title;
    private String content;
    private Category category;
    private QuestMaker questMaker;
    private Timestamp recruitmentTimeStart;
    private Timestamp recruitmentTimeLimit;
    private Timestamp durationTimeStart;
    private Timestamp durationTimeLimit;
    private Timestamp modifyTimeStart;
    private Timestamp modifyTimeLimit;
    private Long participantCapacity;
    private Long reward;
    private String requireCondition;
    private String preferenceCondition;

    public Quest(final Long id,
                 final String title,
                 final String content,
                 final Category category,
                 final QuestMaker questMaker,
                 final Timestamp recruitmentTimeStart,
                 final Timestamp recruitmentTimeLimit,
                 final Timestamp durationTimeStart,
                 final Timestamp durationTimeLimit,
                 final Timestamp modifyTimeStart,
                 final Timestamp modifyTimeLimit,
                 final Long participantCapacity,
                 final Long reward,
                 final String requireCondition,
                 final String preferenceCondition
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.questMaker = questMaker;
        this.recruitmentTimeStart = recruitmentTimeStart;
        this.recruitmentTimeLimit = recruitmentTimeLimit;
        this.durationTimeStart = durationTimeStart;
        this.durationTimeLimit = durationTimeLimit;
        this.modifyTimeStart = modifyTimeStart;
        this.modifyTimeLimit = modifyTimeLimit;
        this.participantCapacity = participantCapacity;
        this.reward = reward;
        this.requireCondition = requireCondition;
        this.preferenceCondition = preferenceCondition;
    }


}
