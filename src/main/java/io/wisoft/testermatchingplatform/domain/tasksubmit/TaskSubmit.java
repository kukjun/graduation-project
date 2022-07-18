package io.wisoft.testermatchingplatform.domain.tasksubmit;

import io.wisoft.testermatchingplatform.domain.submit.Submit;
import io.wisoft.testermatchingplatform.domain.task.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "task_submit")
@NoArgsConstructor
public class TaskSubmit {

    @EmbeddedId
    private TaskSubmitId id;

    private String file_submit_ref;
    private boolean isPass;

    public TaskSubmit(String file_submit_ref, boolean isPass) {
        this.file_submit_ref = file_submit_ref;
        this.isPass = isPass;
    }
}
