package io.wisoft.testermatchingplatform.domain.ntc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NTCRepository extends JpaRepository<NTCEntity, Long> {

    @Query("SELECT n FROM NTCEntity n ORDER BY n.id")
    public List<NTCEntity> findAllDesc();

    @Query("SELECT n FROM NTCEntity n WHERE n.email=?1")
    public Optional<NTCEntity> findByEmail(String email);

    @Query("SELECT n FROM NTCEntity n WHERE n.nickname=?1")
    public Optional<NTCEntity> findByNickname(String nickname);



}
