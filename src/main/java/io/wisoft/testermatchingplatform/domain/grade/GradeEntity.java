package io.wisoft.testermatchingplatform.domain.grade;

import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;

import javax.persistence.*;

@Entity
@Table(name = "GRADE")
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "available_quest_amount", nullable = false)
    private Long availableQuestAmount;

    public GradeEntity(String name, Long availableQuestAmount) {
        this.name = name;
        this.availableQuestAmount = availableQuestAmount;
    }

    public GradeEntity() {

    }

    public Grade toDomain() {
        return new Grade(
                this.id,
                this.name,
                this.availableQuestAmount
        );
    }

    public static GradeEntity from(Grade grade) {
        return new GradeEntity(
                grade.getName(),
                grade.getAvailableQuestAmount()
        );
    }
}
