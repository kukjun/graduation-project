package io.wisoft.testermatchingplatform.domain.submit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmitRepository extends JpaRepository<Submit,Long> {

    List<Submit> findByQuest_Id(final Long id);
}
