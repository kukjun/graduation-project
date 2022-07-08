package io.wisoft.testermatchingplatform.domain.quest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestRepository extends JpaRepository<QuestEntity, Long> {

    @Query("SELECT q FROM QuestEntity q ORDER BY q.id DESC")
    List<Optional<QuestEntity>> findAllDesc();

    @Query("SELECT q FROM QuestEntity q WHERE q.category.name=?1 ORDER BY q.id DESC")
    List<Optional<QuestEntity>> findByCategoryName(final String categoryName);

    @Query("SELECT q FROM QuestEntity q WHERE q.category.id=?1 ORDER BY q.id DESC")
    List<Optional<QuestEntity>> findByCategoryId(final Long categoryId);
}
