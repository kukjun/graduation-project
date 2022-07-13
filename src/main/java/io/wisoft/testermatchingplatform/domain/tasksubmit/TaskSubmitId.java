package io.wisoft.testermatchingplatform.domain.tasksubmit;

import io.wisoft.testermatchingplatform.domain.submit.SubmitEntity;
import io.wisoft.testermatchingplatform.domain.task.TaskEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class TaskSubmitId implements Serializable {

    @JoinColumn(name = "SUBMIT_ID")
    @OneToOne
    private SubmitEntity submit;

    @JoinColumn(name = "TASK_ID")
    @OneToOne
    private TaskEntity task;

    public TaskSubmitId(SubmitEntity submit, TaskEntity task) {
        this.submit = submit;
        this.task = task;
    }
}
