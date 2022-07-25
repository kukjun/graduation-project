package io.wisoft.testermatchingplatform.domain.submit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitRepository extends JpaRepository<Submit,Long> {

    Page<Submit> findByQuest_Id(final Long id, Pageable pageable);

}
