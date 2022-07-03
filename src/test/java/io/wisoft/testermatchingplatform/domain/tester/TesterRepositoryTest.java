package io.wisoft.testermatchingplatform.domain.tester;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.grade.GradeEntity;
import io.wisoft.testermatchingplatform.domain.grade.GradeRepository;
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
class TesterRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TesterRepository testerRepository;

    @BeforeEach
    public void setupData() {
        //given
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

        String categoryName1 = "게임 테스트";
        CategoryEntity categoryEntity1 = new CategoryEntity(categoryName1);
        categoryRepository.save(categoryEntity1);

        String categoryName2 = "기능 테스트";
        CategoryEntity categoryEntity2 = new CategoryEntity(categoryName2);
        categoryRepository.save(categoryEntity2);

        String testerEmail1 = "kukjun@naver.com";
        String testerNickname1 = "kingJun";
        String testerPhoneNumber1 = "010-1234-2345";
        String testerIntroMessage1 = "안녕하세요!!";
        String testerIntroPictureReference1 = "사진 링크1";
        TesterEntity testerEntity1 = new TesterEntity(
                testerEmail1,
                testerNickname1,
                testerPhoneNumber1,
                categoryEntity1,
                testerIntroMessage1,
                testerIntroPictureReference1,
                gradeEntity1
        );
        testerRepository.save(testerEntity1);

        String testerEmail2 = "heeyoung@naver.com";
        String testerNickname2 = "kingyong";
        String testerPhoneNumber2 = "010-4321-9876";
        String testerIntroMessage2 = "안녕희세용!!";
        String testerIntroPictureReference2 = "사진 링크2";
        TesterEntity testerEntity2 = new TesterEntity(
                testerEmail2,
                testerNickname2,
                testerPhoneNumber2,
                categoryEntity2,
                testerIntroMessage2,
                testerIntroPictureReference2,
                gradeEntity2
        );
        testerRepository.save(testerEntity2);

    }

    @AfterEach
    public void cleanup() {
        testerRepository.deleteAll();
        categoryRepository.deleteAll();
        gradeRepository.deleteAll();
    }

    @Test
    public void 테스터_전체_조회() {
        // given

        // when
        List<TesterEntity> list = testerRepository.findAllDesc();

        // then
        assertEquals(2, list.size());
    }

    @Test
    public void 테스터_이메일로_조회() {
        //given
        String testerEmail = "heeyoung@naver.com";
        String testerNickname = "kingyong";
        String testerPhoneNumber = "010-4321-9876";
        String testerIntroMessage = "안녕희세용!!";
        String testerIntroPictureReference = "사진 링크2";
        String categoryName = "기능 테스트";
        String gradeName = "실버";

        // when
        Tester tester = testerRepository.findByEmail(testerEmail).get().toDomain();

        // then
        assertEquals(testerNickname, tester.getNickname());
        assertEquals(testerPhoneNumber, tester.getPhoneNumber());
        assertEquals(testerIntroMessage, tester.getIntroMessage());
        assertEquals(testerIntroPictureReference, tester.getIntroPictureReference());
        assertEquals(categoryRepository.findByName(categoryName).toDomain().getName(), tester.getPreferCategory().getName());
        assertEquals(gradeRepository.findByName(gradeName).toDomain().getName(), tester.getGrade().getName());

    }


}