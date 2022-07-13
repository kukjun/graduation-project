package io.wisoft.testermatchingplatform.domain.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity,Long> {

    List<TaskEntity> findByQuest_Id(Long id);
}
