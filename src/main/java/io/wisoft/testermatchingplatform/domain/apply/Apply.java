package io.wisoft.testermatchingplatform.domain.apply;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Apply {
    private Long id;
    private Timestamp registerTime;
    private Timestamp permissionTime;
    private Tester tester;
    private Quest quest;
    private String requireConditionSubmitRef;
    private String preferenceConditionSubmitRef;
}
