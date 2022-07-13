package io.wisoft.testermatchingplatform.domain.submit;

import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Submit {

    private Long id;
    private Date registerTime;
    private Date reportTime;
    private Tester tester;
    private Quest quest;
}
