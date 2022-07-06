package io.wisoft.testermatchingplatform.domain.questmaker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestMakerRepository extends JpaRepository<QuestMakerEntity, Long> {

    @Query("SELECT n FROM QuestMakerEntity n ORDER BY n.id")
    public List<QuestMakerEntity> findAllDesc();

    @Query("SELECT n FROM QuestMakerEntity n WHERE n.email=?1")
    public Optional<QuestMakerEntity> findByEmail(String email);

    @Query("SELECT n FROM QuestMakerEntity n WHERE n.nickname=?1")
    public Optional<QuestMakerEntity> findByNickname(String nickname);



}
