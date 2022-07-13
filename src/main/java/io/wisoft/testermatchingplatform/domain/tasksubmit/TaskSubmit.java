package io.wisoft.testermatchingplatform.domain.tasksubmit;

import io.wisoft.testermatchingplatform.domain.submit.Submit;
import io.wisoft.testermatchingplatform.domain.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskSubmit {

    private Submit submit;
    private Task task;
    private String file_submit_ref;
    private boolean isPass;
}
