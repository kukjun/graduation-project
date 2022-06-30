package io.wisoft.testermatchingplatform.domain.ntc;

import io.wisoft.testermatchingplatform.domain.category.CategoryEntity;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.handler.exception.EmailOverlapException;
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
class NTCRepositoryTest {

    @Autowired
    NTCRepository ntcRepository;

    @BeforeEach
    public void dataSetup() {
        // given
        String ntcEmail1 = "kukjun@test.com";
        String ntcPassword1 = "1234";
        String ntcNickname1 = "킹준";
        String ntcPhoneNumber1 = "010-1234-8359";
        NTCEntity ntcEntity1 = new NTCEntity(ntcEmail1, ntcPassword1, ntcNickname1, ntcPhoneNumber1);
        ntcRepository.save(ntcEntity1);

        String ntcEmail2 = "heyoung@test.com";
        String ntcPassword2 = "4321";
        String ntcNickname2 = "킹희영";
        String ntcPhoneNumber2 = "010-1234-5678";
        NTCEntity ntcEntity2 = new NTCEntity(ntcEmail2, ntcPassword2, ntcNickname2, ntcPhoneNumber2);
        ntcRepository.save(ntcEntity2);
    }

    @AfterEach
    public void cleanup() {
        ntcRepository.deleteAll();
    }

    @Test
    public void NTC_전체_조회() {
        //given

        // when
        List<NTCEntity> list = ntcRepository.findAllDesc();

        // then
        assertEquals(2, list.size());

    }

    @Test
    public void NTC_이메일로_조회() {
        //given
        String ntcEmail = "kukjun@test.com";
        String ntcNickname = "킹준";
        String ntcPhoneNumber = "010-1234-8359";

        // when
        NTC ntc = ntcRepository.findByEmail(ntcEmail).orElseThrow(
                () -> new EmailOverlapException(ntcEmail + "은 이미 가입된 이메일입니다.")
        ).toDomain();

        // given
        assertEquals(ntcEmail, ntc.getEmail());
        assertEquals(ntcNickname, ntc.getNickname());
        assertEquals(ntcPhoneNumber, ntc.getPhoneNumber());
    }

    @Test
    public void NTC_닉네임으로_조회() {
        //given
        String ntcEmail = "kukjun@test.com";
        String ntcNickname = "킹준";
        String ntcPhoneNumber = "010-1234-8359";

        // when
        // 예외 처리 추가
        NTC ntc = ntcRepository.findByNickname(ntcNickname).get().toDomain();

        // given
        assertEquals(ntcEmail, ntc.getEmail());
        assertEquals(ntcNickname, ntc.getNickname());
        assertEquals(ntcPhoneNumber, ntc.getPhoneNumber());
    }



}