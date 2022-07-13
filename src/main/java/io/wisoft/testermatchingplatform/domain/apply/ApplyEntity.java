package io.wisoft.testermatchingplatform.domain.apply;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.wisoft.testermatchingplatform.domain.quest.QuestEntity;
import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "apply")
@NoArgsConstructor
public class ApplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp registerTime;
    private Timestamp permissionTime;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "TESTER_ID")
    private TesterEntity tester;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "QUEST_ID")
    private QuestEntity quest;
    private String requireConditionSubmitRef;
    private String preferenceConditionSubmitRef;

    public ApplyEntity(Long id, Timestamp registerTime, Timestamp permissionTime, TesterEntity tester, QuestEntity quest, String requireConditionSubmitRef, String preferenceConditionSubmitRef) {
        this.id = id;
        this.registerTime = registerTime;
        this.permissionTime = permissionTime;
        this.tester = tester;
        this.quest = quest;
        this.requireConditionSubmitRef = requireConditionSubmitRef;
        this.preferenceConditionSubmitRef = preferenceConditionSubmitRef;
    }

    public static ApplyEntity from(final Apply apply){
        return new ApplyEntity(
                apply.getId(),
                apply.getRegisterTime(),
                apply.getPermissionTime(),
                TesterEntity.from(apply.getTester()),
                QuestEntity.from(apply.getQuest()),
                apply.getRequireConditionSubmitRef(),
                apply.getPreferenceConditionSubmitRef()
        );
    }

    public Apply toDomain(){
        return new Apply(
                this.id,
                this.registerTime,
                this.permissionTime,
                this.tester.toDomain(),
                this.quest.toDomain(),
                this.requireConditionSubmitRef,
                this.preferenceConditionSubmitRef
        );
    }
}
