package io.wisoft.testermatchingplatform.domain.quest;


import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.ntc.NTC;
import io.wisoft.testermatchingplatform.domain.ntc.NTCEntity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "QUESTS")
public class QuestEntity {

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

    @JoinColumn(name = "ntc_id")
    @ManyToOne
    private NTCEntity ntc;

    @Column(name = "register_time", nullable = false)
    private Timestamp registerTime;

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

    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Column(name = "payment_point", nullable = false)
    private Long paymentPoint;

    @Column(name = "require_condition")
    private String requireCondition;

    @Column(name = "preference_condition")
    private String preferenceCondition;

    public QuestEntity(String title, String content, CategoryEntity category, NTCEntity ntc, Timestamp registerTime, Timestamp recruitmentTimeStart, Timestamp recruitmentTimeLimit, Timestamp durationTimeStart, Timestamp durationTimeLimit, Timestamp modifyTimeStart, Timestamp modifyTimeLimit, Long capacity, Long paymentPoint) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.ntc = ntc;
        this.registerTime = registerTime;
        this.recruitmentTimeStart = recruitmentTimeStart;
        this.recruitmentTimeLimit = recruitmentTimeLimit;
        this.durationTimeStart = durationTimeStart;
        this.durationTimeLimit = durationTimeLimit;
        this.modifyTimeStart = modifyTimeStart;
        this.modifyTimeLimit = modifyTimeLimit;
        this.capacity = capacity;
        this.paymentPoint = paymentPoint;
    }

    public QuestEntity() {
    }

    // Quest <-> QuestEntity
    public static QuestEntity from(final Quest quest) {
        return new QuestEntity(
                quest.getTitle(),
                quest.getContent(),
                CategoryEntity.from(quest.getCategory()),
                NTCEntity.from(quest.getNtc()),
                quest.getRegisterTime(),
                quest.getRecruitmentTimeStart(),
                quest.getRecruitmentTimeLimit(),
                quest.getDurationTimeStart(),
                quest.getDurationTimeLimit(),
                quest.getModifyTimeStart(),
                quest.getModifyTimeLimit(),
                quest.getCapacity(),
                quest.getPaymentPoint()
        );
    }

    public Quest toDomain() {
        return new Quest(
                this.id,
                this.title,
                this.content,
                this.category.toDomain(),
                this.ntc.toDomain(),
                this.registerTime,
                this.recruitmentTimeStart,
                this.recruitmentTimeLimit,
                this.durationTimeStart,
                this.durationTimeLimit,
                this.modifyTimeStart,
                this.modifyTimeLimit,
                this.capacity,
                this.paymentPoint,
                this.requireCondition,
                this.preferenceCondition
        );
    }

}
