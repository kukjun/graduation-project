package io.wisoft.testermatchingplatform.domain.tasksubmit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TaskSubmitRepository extends JpaRepository<TaskSubmit,Long> {

    List<TaskSubmit> findById_Submit(Long id);
}
