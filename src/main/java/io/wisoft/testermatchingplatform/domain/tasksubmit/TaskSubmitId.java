package io.wisoft.testermatchingplatform.domain.tasksubmit;

import io.wisoft.testermatchingplatform.domain.submit.Submit;
import io.wisoft.testermatchingplatform.domain.task.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class TaskSubmitId implements Serializable {

    @JoinColumn(name = "SUBMIT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Submit submit;

    @JoinColumn(name = "TASK_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Task task;

    public TaskSubmitId(Submit submit, Task task) {
        this.submit = submit;
        this.task = task;
    }
}
