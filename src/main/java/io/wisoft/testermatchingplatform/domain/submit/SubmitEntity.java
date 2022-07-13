package io.wisoft.testermatchingplatform.domain.submit;

import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "submit")
@Data
@NoArgsConstructor
public class SubmitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp registerTime;
    private Timestamp reportTime;
    @JoinColumn(name = "TESTER_ID")
    @ManyToOne
    private TesterEntity tester;
    @JoinColumn(name = "QUEST_ID")
    @ManyToOne
    private QuestEntity quest;

    public static SubmitEntity from(final Submit submit){
        return new SubmitEntity(
                submit.getId(),
                submit.getRegisterTime(),
                submit.getReportTime(),
                TesterEntity.from(submit.getTester()),
                QuestEntity.from(submit.getQuest())
        );
    }

    public SubmitEntity(Long id, Timestamp registerTime, Timestamp reportTime, TesterEntity tester, QuestEntity quest) {
        this.id = id;
        this.registerTime = registerTime;
        this.reportTime = reportTime;
        this.tester = tester;
        this.quest = quest;
    }

    public Submit toDomain(){
        return new Submit(
                this.id,
                this.registerTime,
                this.reportTime,
                this.tester == null ? null : this.tester.toDomain(),
                this.quest == null ? null : this.quest.toDomain()
        );
    }
}
