package io.wisoft.testermatchingplatform.domain.quest;

import io.wisoft.testermatchingplatform.domain.BaseTime;
import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.questmaker.QuestMaker;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quest")
@NoArgsConstructor
public class Quest extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Embedded
    private QuestRelateTime questRelateTime;
    @ManyToOne
    @JoinColumn(name = "QUEST_MAKER_ID")
    private QuestMaker questMaker;
    private int participantCapacity;
    private int reward;
    private String requireCondition;
    private String preferenceCondition;

    public Quest(Long id, String title, String content, Category category, QuestRelateTime questRelateTime, QuestMaker questMaker, int participantCapacity, int reward, String requireCondition, String preferenceCondition) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.questRelateTime = questRelateTime;
        this.questMaker = questMaker;
        this.participantCapacity = participantCapacity;
        this.reward = reward;
        this.requireCondition = requireCondition;
        this.preferenceCondition = preferenceCondition;
    }

    public Quest(String title, String content, Category category, QuestRelateTime questRelateTime, QuestMaker questMaker, int participantCapacity, int reward, String requireCondition, String preferenceCondition) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.questRelateTime = questRelateTime;
        this.questMaker = questMaker;
        this.participantCapacity = participantCapacity;
        this.reward = reward;
        this.requireCondition = requireCondition;
        this.preferenceCondition = preferenceCondition;
    }
}
