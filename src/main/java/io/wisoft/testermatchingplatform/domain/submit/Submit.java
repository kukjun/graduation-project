package io.wisoft.testermatchingplatform.domain.submit;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Submit {

    private Long id;
    private Timestamp registerTime;
    private Timestamp reportTime;
    private Tester tester;
    private Quest quest;
}
