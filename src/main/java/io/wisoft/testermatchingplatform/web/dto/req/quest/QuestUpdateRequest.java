package io.wisoft.testermatchingplatform.web.dto.req.quest;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestUpdateRequest {

    private String title;
    private String content;
    private String categoryName;
    private Timestamp registerTime;
    private Timestamp recruitmentTimeStart;
    private Timestamp recruitmentTimeLimit;
    private Timestamp durationTimeStart;
    private Timestamp durationTimeLimit;
    private Timestamp modifyTimeStart;
    private Timestamp modifyTimeLimit;
    private int participantCapacity;
    private int reward;
    private String requireCondition;
    private String preferenceCondition;
}
