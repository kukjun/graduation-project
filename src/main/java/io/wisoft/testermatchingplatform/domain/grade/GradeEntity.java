package io.wisoft.testermatchingplatform.domain.grade;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grade")
@NoArgsConstructor
public class GradeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long availableQuestAmount;

    public static GradeEntity from(final Grade grade){
        return new GradeEntity(
                grade.getId(),
                grade.getName(),
                grade.getAvailable_quest_amount()
        );
    }

    public GradeEntity(Long id, String name, Long availableQuestAmount) {
        this.id = id;
        this.name = name;
        this.availableQuestAmount = availableQuestAmount;
    }

    public Grade toDomain(){
        return new Grade(
                this.id,
                this.name,
                this.availableQuestAmount
        );
    }
}
