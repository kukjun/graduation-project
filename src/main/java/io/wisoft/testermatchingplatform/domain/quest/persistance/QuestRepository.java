package io.wisoft.testermatchingplatform.domain.quest.persistance;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestRepository extends JpaRepository<QuestEntity, Long> {

    @Query("SELECT q FROM QuestEntity q ORDER BY q.id DESC")
    List<QuestEntity> findAllDesc();

}
