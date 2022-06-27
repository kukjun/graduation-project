package io.wisoft.testermatchingplatform.domain.grade;

import lombok.Getter;

@Getter
public class Grade {
    private Long id;
    private String name;
    private Long availableQuestAmount;

    public Grade(Long id, String name, Long availableQuestAmount) {
        this.id = id;
        this.name = name;
        this.availableQuestAmount = availableQuestAmount;
    }
}
