package io.wisoft.testermatchingplatform.domain.quest;

import io.wisoft.testermatchingplatform.domain.apply.ApplyEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "quest")
@NoArgsConstructor
public class QuestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;
    private Timestamp registerTime;
    private Timestamp recruitmentTimeStart;
    private Timestamp recruitmentTimeLimit;
    private Timestamp durationTimeStart;
    private Timestamp durationTimeLimit;
    private Timestamp modifyTimeStart;
    private Timestamp modifyTimeLimit;
    @ManyToOne
    @JoinColumn(name = "QUEST_MAKER_ID")
    private QuestMakerEntity questMaker;
    private int participantCapacity;
    private int reward;
    private String requireCondition;
    private String preferenceCondition;
    
    public Quest toDomain() {
        return new Quest(
                this.id,
                this.title,
                this.content,
                this.category == null ? null :this.category.toDomain(),
                this.registerTime,
                this.recruitmentTimeStart,
                this.recruitmentTimeLimit,
                this.durationTimeStart,
                this.durationTimeLimit,
                this.modifyTimeStart,
                this.modifyTimeLimit,
                this.questMaker == null ? null:this.questMaker.toDomain(),
                this.participantCapacity,
                this.reward,
                this.requireCondition,
                this.preferenceCondition
        );
    }

    public static QuestEntity from(final Quest quest){
        return new QuestEntity(
                quest.getId(),
                quest.getTitle(),
                quest.getContent(),
                CategoryEntity.from(quest.getCategory()),
                quest.getRegisterTime(),
                quest.getRecruitmentTimeStart(),
                quest.getRecruitmentTimeLimit(),
                quest.getDurationTimeStart(),
                quest.getDurationTimeLimit(),
                quest.getModifyTimeStart(),
                quest.getModifyTimeLimit(),
                QuestMakerEntity.from(quest.getQuestMaker()),
                quest.getParticipantCapacity(),
                quest.getReward(),
                quest.getRequireCondition(),
                quest.getPreferenceCondition()
        );
    }

    public QuestEntity(Long id, String title, String content, CategoryEntity category, Timestamp registerTime, Timestamp recruitmentTimeStart, Timestamp recruitmentTimeLimit, Timestamp durationTimeStart, Timestamp durationTimeLimit, Timestamp modifyTimeStart, Timestamp modifyTimeLimit, QuestMakerEntity questMaker, int participantCapacity, int reward, String requireCondition, String preferenceCondition) {
        this.id = id;
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
