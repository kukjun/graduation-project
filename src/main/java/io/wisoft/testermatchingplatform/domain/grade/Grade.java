package io.wisoft.testermatchingplatform.domain.grade;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "grade")
@NoArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long availableQuestAmount;

    public Grade(String name, Long availableQuestAmount) {
        this.name = name;
        this.availableQuestAmount = availableQuestAmount;
    }

    public static Long initialTesterGrade() {
        return 1L;
    }
}
