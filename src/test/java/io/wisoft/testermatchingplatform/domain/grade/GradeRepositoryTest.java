package io.wisoft.testermatchingplatform.domain.grade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;

    @BeforeEach
    public void dataSetup() {
        String gradeName1 = "브론즈";
        Long gradeAvailableQuestAmount1 = 0L;
        GradeEntity gradeEntity1 = new GradeEntity(gradeName1, gradeAvailableQuestAmount1);
        gradeRepository.save(gradeEntity1);

        String gradeName2 = "실버";
        Long gradeAvailableQuestAmount2 = 1000L;
        GradeEntity gradeEntity2 = new GradeEntity(gradeName2, gradeAvailableQuestAmount2);
        gradeRepository.save(gradeEntity2);

        String gradeName3 = "골드";
        Long gradeAvailableQuestAmount3 = 5000L;
        GradeEntity gradeEntity3 = new GradeEntity(gradeName3, gradeAvailableQuestAmount3);
        gradeRepository.save(gradeEntity3);
    }

    @Test
    public void Grade_전체조회() {
        List<GradeEntity> list = gradeRepository.findAllDesc();
        assertEquals(3, list.size());
    }

    @Test
    public void Grade_이름으로_조회() {
        Grade silverGrade = gradeRepository.findByName("실버").toDomain();
        assertEquals("실버", silverGrade.getName());
        assertEquals(1000L, silverGrade.getAvailableQuestAmount());

    }

}