package io.wisoft.testermatchingplatform.domain.grade;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface GradeRepository extends JpaRepository<Grade,Long> {

}
