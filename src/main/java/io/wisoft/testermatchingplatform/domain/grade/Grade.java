package io.wisoft.testermatchingplatform.domain.grade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Grade {
    private Long id;
    private String name;
    private Long available_quest_amount;


    public static Long initialTesterGrade() {
        return 1L;
    }
}
