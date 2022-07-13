package io.wisoft.testermatchingplatform.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity,Long> {
}
