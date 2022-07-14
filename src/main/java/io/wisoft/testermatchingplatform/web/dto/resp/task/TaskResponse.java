package io.wisoft.testermatchingplatform.web.dto.resp.task;

import io.wisoft.testermatchingplatform.domain.task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponse {

    private Long taskNumber;
    private String taskExampleRef;
    private String require;

    public static TaskResponse from(final Task task){
        return new TaskResponse(
                task.getTaskNumber(),
                task.getTaskExampleRef(),
                task.getRequire()
        );
    }
}
