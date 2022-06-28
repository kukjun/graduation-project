package io.wisoft.testermatchingplatform.domain.category;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    public void dataSetup() {
        String name1 = "게임 테스트";
        categoryRepository.save(new CategoryEntity(name1));

        String name2 = "기능 테스트";
        categoryRepository.save(new CategoryEntity(name2));
    }

    @AfterEach
    public void cleanup() {
        categoryRepository.deleteAll();
    }

    @Test
    public void 카테고리_전체_조회() {
        // given

        // when
        List<CategoryEntity> list = categoryRepository.findAllDesc();

        // then
        assertEquals(2, list.size());
    }

    @Test
    public void 카테고리_이름으로_조회() {
        // given
        String name = "게임 테스트";

        // when
        Category category = categoryRepository.findByName(name).toDomain();

        // then
        assertEquals(name, category.getName());
    }

}