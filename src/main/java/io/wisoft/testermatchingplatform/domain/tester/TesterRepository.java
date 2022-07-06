package io.wisoft.testermatchingplatform.domain.tester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TesterRepository extends JpaRepository<TesterEntity, Long> {

    @Query("SELECT t FROM TesterEntity t ORDER BY t.id DESC")
    public List<TesterEntity> findAllDesc();

    @Query("SELECT t FROM TesterEntity t WHERE t.email = ?1")
    public Optional<TesterEntity> findByEmail(String email);

    @Query("SELECT t FROM TesterEntity t WHERE t.nickname = ?1")
    public Optional<TesterEntity> findByNickname(String nickname);


}
