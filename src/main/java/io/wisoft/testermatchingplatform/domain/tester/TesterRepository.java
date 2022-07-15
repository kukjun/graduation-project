package io.wisoft.testermatchingplatform.domain.tester;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TesterRepository extends JpaRepository<TesterEntity,Long> {

    public Optional<TesterEntity> findByEmail(String email);

    public boolean existsByEmailAndPassword(String email, String password);

    public boolean existsByEmail(String email);

    public boolean existsByNickname(String nickname);

}
