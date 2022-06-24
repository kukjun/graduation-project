package io.wisoft.testermatchingplatform.domain.quest.persistance;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @AfterEach
    public void cleanup() {
        categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리_이름_가져오는_테스트() {
        // given
        Long id = 1L;
        String name = "게임 테스트";

        categoryRepository.save(new CategoryEntity(name));

        // when
        Category category = categoryRepository.getReferenceById(id).toDomain();

        // then
        assertEquals(name, category.getName());

    }

}