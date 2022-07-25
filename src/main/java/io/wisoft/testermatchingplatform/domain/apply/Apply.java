package io.wisoft.testermatchingplatform.domain.apply;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.wisoft.testermatchingplatform.domain.BaseTime;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "apply")
@NoArgsConstructor
@NamedEntityGraph(name = "applyWithTester",attributeNodes = {@NamedAttributeNode("tester")})
public class Apply extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp permissionTime;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "TESTER_ID")
    private Tester tester;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "QUEST_ID")
    private Quest quest;
    private String requireConditionSubmitRef;
    private String preferenceConditionSubmitRef;

    public Apply(Timestamp permissionTime, Tester tester, Quest quest, String requireConditionSubmitRef, String preferenceConditionSubmitRef) {
        this.permissionTime = permissionTime;
        this.tester = tester;
        this.quest = quest;
        this.requireConditionSubmitRef = requireConditionSubmitRef;
        this.preferenceConditionSubmitRef = preferenceConditionSubmitRef;
    }
}
