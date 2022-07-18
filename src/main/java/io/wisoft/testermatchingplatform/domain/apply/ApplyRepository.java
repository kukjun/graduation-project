package io.wisoft.testermatchingplatform.domain.apply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Long> {

    List<Apply> findByQuest_Id(Long id);
}
