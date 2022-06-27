package io.wisoft.testermatchingplatform.domain.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {

    @Query("SELECT g FROM GradeEntity g ORDER BY g.id")
    public List<GradeEntity> findAllDesc();

    @Query("SELECT g FROM GradeEntity g WHERE g.name=?1 ORDER BY g.id")
    public GradeEntity findByName(String name);

}
