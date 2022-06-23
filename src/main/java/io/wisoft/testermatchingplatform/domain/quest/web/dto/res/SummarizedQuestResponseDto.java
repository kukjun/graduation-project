package io.wisoft.testermatchingplatform.domain.quest.web.dto.res;

import io.wisoft.testermatchingplatform.domain.quest.Category;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class SummarizedQuestResponseDto {

    private Long id;
    private String title;
    private String categoryName;
    private Timestamp registerTime;
    private Timestamp recruitmentTimeLimit;
    private Timestamp durationTimeStart;
    private Timestamp durationTimeLimit;
    private Long capacity;
    private Long paymentPoint;

    public SummarizedQuestResponseDto(Quest quest, Category category) {
        this.id = quest.getId();
        this.title = quest.getTitle();
        this.categoryName = category.getName();
        this.registerTime = quest.getRegisterTime();
        this.recruitmentTimeLimit = quest.getRecruitmentTimeLimit();
        this.durationTimeStart = quest.getDurationTimeStart();
        this.durationTimeLimit = quest.getDurationTimeLimit();
        this.capacity = quest.getCapacity();
        this.paymentPoint = quest.getPaymentPoint();
    }


}
