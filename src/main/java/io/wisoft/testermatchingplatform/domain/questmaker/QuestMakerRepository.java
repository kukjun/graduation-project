package io.wisoft.testermatchingplatform.domain.questmaker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestMakerRepository extends JpaRepository<QuestMaker,Long> {


    @Override
    Optional<QuestMaker> findById(Long id);

    Optional<QuestMaker> findByEmail(String email);
}
