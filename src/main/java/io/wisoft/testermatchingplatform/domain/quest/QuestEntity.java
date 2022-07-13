package io.wisoft.testermatchingplatform.domain.quest;


import io.wisoft.testermatchingplatform.domain.BaseTimeEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMakerEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "QUEST")
public class QuestEntity extends BaseTimeEntity {

    // 기본키 매핑
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 40)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private CategoryEntity category;

    @JoinColumn(name = "quest_maker_id")
    @ManyToOne
    private QuestMakerEntity questMaker;

    @Column(name = "recruitment_time_start", nullable = false)
    private Timestamp recruitmentTimeStart;

    @Column(name = "recruitment_time_limit", nullable = false)
    private Timestamp recruitmentTimeLimit;

    @Column(name = "duration_time_start", nullable = false)
    private Timestamp durationTimeStart;

    @Column(name = "duration_time_limit", nullable = false)
    private Timestamp durationTimeLimit;

    @Column(name = "modify_time_start", nullable = false)
    private Timestamp modifyTimeStart;

    @Column(name = "modify_time_limit", nullable = false)
    private Timestamp modifyTimeLimit;

    @Column(name = "participant_capacity", nullable = false)
    private Long participantCapacity;

    @Column(name = "reward", nullable = false)
    private Long reward;

    @Column(name = "require_condition")
    private String requireCondition;

    @Column(name = "preference_condition")
    private String preferenceCondition;

    public QuestEntity(Long id, String title, String content, CategoryEntity category, QuestMakerEntity questMaker, Timestamp recruitmentTimeStart, Timestamp recruitmentTimeLimit, Timestamp durationTimeStart, Timestamp durationTimeLimit, Timestamp modifyTimeStart, Timestamp modifyTimeLimit, Long participantCapacity, Long reward, String requireCondition, String preferenceCondition) {
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

    public QuestEntity(String title, String content, CategoryEntity category, QuestMakerEntity questMaker, Timestamp recruitmentTimeStart, Timestamp recruitmentTimeLimit, Timestamp durationTimeStart, Timestamp durationTimeLimit, Timestamp modifyTimeStart, Timestamp modifyTimeLimit, Long participantCapacity, Long reward, String requireCondition, String preferenceCondition) {
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

    public QuestEntity(String title, String content, CategoryEntity category, QuestMakerEntity questMaker, Timestamp recruitmentTimeStart, Timestamp recruitmentTimeLimit, Timestamp durationTimeStart, Timestamp durationTimeLimit, Timestamp modifyTimeStart, Timestamp modifyTimeLimit, Long participantCapacity, Long reward) {
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
    }

    public QuestEntity() {
    }

    // Quest <-> QuestEntity
    public static QuestEntity from(final Quest quest) {
        return new QuestEntity(
                quest.getTitle(),
                quest.getContent(),
                CategoryEntity.from(quest.getCategory()),
                QuestMakerEntity.from(quest.getQuestMaker()),
                quest.getRecruitmentTimeStart(),
                quest.getRecruitmentTimeLimit(),
                quest.getDurationTimeStart(),
                quest.getDurationTimeLimit(),
                quest.getModifyTimeStart(),
                quest.getModifyTimeLimit(),
                quest.getParticipantCapacity(),
                quest.getReward()
        );
    }

    public Quest toDomain() {
        return new Quest(
                this.id,
                this.title,
                this.content,
                this.category == null ? null : this.category.toDomain(),
                this.questMaker.toDomain(),
                this.recruitmentTimeStart,
                this.recruitmentTimeLimit,
                this.durationTimeStart,
                this.durationTimeLimit,
                this.modifyTimeStart,
                this.modifyTimeLimit,
                this.participantCapacity,
                this.reward,
                this.requireCondition,
                this.preferenceCondition
        );
    }

}
