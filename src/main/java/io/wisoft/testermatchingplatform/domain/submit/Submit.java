package io.wisoft.testermatchingplatform.domain.submit;

import io.wisoft.testermatchingplatform.domain.BaseTime;
import io.wisoft.testermatchingplatform.domain.quest.Quest;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "submit")
@Data
@NoArgsConstructor
@NamedEntityGraph(name = "SubmitWithTester",attributeNodes = {@NamedAttributeNode("tester")})
public class Submit extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp reportTime;
    @JoinColumn(name = "TESTER_ID")
    @ManyToOne
    private Tester tester;
    @JoinColumn(name = "QUEST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quest quest;

    public Submit(Timestamp reportTime, Tester tester, Quest quest) {
        this.reportTime = reportTime;
        this.tester = tester;
        this.quest = quest;
    }
}
