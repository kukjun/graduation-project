package io.wisoft.testermatchingplatform.domain.quest;

import io.wisoft.testermatchingplatform.domain.quest.persistance.QuestEntity;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Quest {

    private Long id;
    private String title;
    private String content;
    private Long categoryId;
    private Long ntcId;
    private Timestamp registerTime;
    private Timestamp recruitmentTimeStart;
    private Timestamp recruitmentTimeLimit;
    private Timestamp durationTimeStart;
    private Timestamp durationTimeLimit;
    private Timestamp modifyTimeStart;
    private Timestamp modifyTimeLimit;
    private Long capacity;
    private Long paymentPoint;
    private String requireCondition;
    private String preferenceCondition;

    public Quest(final String title,
                 final String content,
                 final Long categoryId,
                 final Long ntcId,
                 final Timestamp registerTime,
                 final Timestamp recruitmentTimeStart,
                 final Timestamp recruitmentTimeLimit,
                 final Timestamp durationTimeStart,
                 final Timestamp durationTimeLimit,
                 final Timestamp modifyTimeStart,
                 final Timestamp modifyTimeLimit,
                 final Long capacity,
                 final Long paymentPoint,
                 final String requireCondition,
                 final String preferenceCondition
    ) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.ntcId = ntcId;
        this.registerTime = registerTime;
        this.recruitmentTimeStart = recruitmentTimeStart;
        this.recruitmentTimeLimit = recruitmentTimeLimit;
        this.durationTimeStart = durationTimeStart;
        this.durationTimeLimit = durationTimeLimit;
        this.modifyTimeStart = modifyTimeStart;
        this.modifyTimeLimit = modifyTimeLimit;
        this.capacity = capacity;
        this.paymentPoint = paymentPoint;
        this.requireCondition = requireCondition;
        this.preferenceCondition = preferenceCondition;
    }


}
