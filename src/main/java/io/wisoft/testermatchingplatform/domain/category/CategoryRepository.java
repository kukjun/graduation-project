package io.wisoft.testermatchingplatform.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c ORDER BY c.id")
    public List<CategoryEntity> findAllDesc();

    @Query("SELECT c FROM CategoryEntity c WHERE c.name = ?1 ORDER BY c.id")
    public CategoryEntity findByName(String name);
}
