package io.wisoft.testermatchingplatform.web.dto.response;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class DetailQuestResponseDto {

    private final String title;
    private final String content;
    private final String categoryName;
    private final String questMakerNickname;
    private final Timestamp recruitmentTimeStart;
    private final Timestamp recruitmentTimeLimit;
    private final Timestamp durationTimeStart;
    private final Timestamp durationTimeLimit;
    private final Timestamp modifyTimeStart;
    private final Timestamp modifyTimeLimit;
    private final Long participantCapacity;
    private final Long reward;
    private final String requireCondition;
    private final String preferenceCondition;

    public DetailQuestResponseDto(Quest quest) {
        this.title = quest.getTitle();
        this.content = quest.getContent();
        this.categoryName = quest.getCategory().getName();
        this.questMakerNickname = quest.getQuestMaker().getNickname();
        this.recruitmentTimeStart = quest.getRecruitmentTimeStart();
        this.recruitmentTimeLimit = quest.getRecruitmentTimeLimit();
        this.durationTimeStart = quest.getDurationTimeStart();
        this.durationTimeLimit = quest.getDurationTimeLimit();
        this.modifyTimeStart = quest.getModifyTimeStart();
        this.modifyTimeLimit = quest.getModifyTimeLimit();
        this.participantCapacity = quest.getParticipantCapacity();
        this.reward = quest.getReward();
        this.requireCondition = quest.getRequireCondition();
        this.preferenceCondition = quest.getPreferenceCondition();
    }
}
