package io.wisoft.testermatchingplatform.web.dto.response;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class SummarizedQuestResponseDto {

    private final Long id;
    private final String title;
    private final String categoryName;
    private final Timestamp recruitmentTimeLimit;
    private final Timestamp durationTimeStart;
    private final Timestamp durationTimeLimit;
    private final Long capacity;
    private final Long paymentPoint;

    public SummarizedQuestResponseDto(Quest quest) {
        this.id = quest.getId();
        this.title = quest.getTitle();
        this.categoryName = quest.getCategory().getName();
        this.recruitmentTimeLimit = quest.getRecruitmentTimeLimit();
        this.durationTimeStart = quest.getDurationTimeStart();
        this.durationTimeLimit = quest.getDurationTimeLimit();
        this.capacity = quest.getParticipantCapacity();
        this.paymentPoint = quest.getReward();
    }


}
