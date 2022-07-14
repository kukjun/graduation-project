package io.wisoft.testermatchingplatform.service.tester;

import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import io.wisoft.testermatchingplatform.domain.grade.GradeRepository;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import io.wisoft.testermatchingplatform.domain.tester.TesterRepository;
import io.wisoft.testermatchingplatform.handler.FileHandler;
import io.wisoft.testermatchingplatform.handler.exception.auth.NicknameOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.category.CategoryNotFoundException;
import io.wisoft.testermatchingplatform.handler.exception.tester.GradeNotFoundException;
import io.wisoft.testermatchingplatform.handler.exception.tester.TesterAuthException;
import io.wisoft.testermatchingplatform.handler.exception.tester.TesterNotFoundException;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterSignUpRequest;
import io.wisoft.testermatchingplatform.web.dto.resp.tester.DetailTesterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TesterManageService {
    private final TesterRepository testerRepository;
    private final CategoryRepository categoryRepository;
    private final GradeRepository gradeRepository;


//    @Transactional
//    public void deleteTester(Long testerId) {
//        if (testerRepository.existsById(testerId)) {
//            testerRepository.deleteById(testerId);
//        } else {
//            throw new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다.");
//        }
//    }

    @Transactional(readOnly = true)
    public DetailTesterResponse findByTesterId(Long testerId) {

        Tester tester = testerRepository.findById(testerId)
                .orElseThrow(() -> new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다."))
                .toDomain();

        return new DetailTesterResponse(tester);
    }


//    @Transactional
//    public Long updateTester(TesterUpdateRequest testerUpdateRequest) {
//
//        Tester tester = testerRepository.findById(testerUpdateRequest.getId()).orElseThrow(
//                        () -> new TesterNotFoundException("tester ID: " + testerUpdateRequest.getId() + "를 찾을 수 없습니다."))
//                .toDomain();
//
//        if (testerRepository.findByEmail(testerUpdateRequest.getEmail()).isPresent()) {
//            throw new EmailOverlapException(testerUpdateRequest.getEmail() + "은 중복입니다.");
//        }
//        tester.setEmail(testerUpdateRequest.getEmail());
//        tester.setPassword(testerUpdateRequest.getPassword());
//        if (testerRepository.findByNickname(testerUpdateRequest.getNickname()).isPresent()) {
//            throw new NicknameOverlapException(testerUpdateRequest.getNickname() + "은 중복입니다.");
//        }
//        tester.setPhoneNumber(testerUpdateRequest.getPhoneNumber());
//        tester.setPreferCategory(
//                categoryRepository.findByName(testerUpdateRequest.getPreferCategoryName())
//                        .orElseThrow(
//                                () -> new CategoryNotFoundException(testerUpdateRequest.getPreferCategoryName() + "를 찾을 수 없음")
//                        ).toDomain()
//        );
//            tester.setIntroMessage(testerUpdateRequest.getIntroMessage());
//        if (!testerUpdateRequest.getIntroPicture().isEmpty()) {
////            이전 사진을 제거하고 새로운 사진을 저장할 수 있도록 하는 로직 필요
//            String profilePath = FileHandler.saveFileData(testerUpdateRequest.getIntroPicture());
//            tester.setIntroPictureReference(profilePath);
//        }
//
//        return testerRepository.save(TesterEntity.from(tester)).toDomain().getId();
//    }

    @Transactional
    public Long registerTester(TesterSignUpRequest testerRequest) {

        if (!testerRepository.existsByEmail(testerRequest.getEmail())) {
            throw new TesterAuthException(testerRequest.getEmail() + ": Email Overlap");
        }
        if (!testerRepository.existsByNickname(testerRequest.getNickname())) {
            throw new NicknameOverlapException(testerRequest.getNickname() + ": Nickname Overlap");
        }

        final String profileImagePath = FileHandler.saveFileData(testerRequest.getIntroPicture());

        Tester tester = new Tester(
                testerRequest.getEmail(),
                testerRequest.getPassword(),
                testerRequest.getNickname(),
                testerRequest.getPhoneNumber(),
                categoryRepository.findById(testerRequest.getPreferCategoryId())
                        .orElseThrow(
                                () -> new CategoryNotFoundException(testerRequest.getPreferCategoryId() + "인 카테고리를 찾을 수 없습니다."))
                        .toDomain(),
                testerRequest.getIntroMessage(),
                profileImagePath,
                gradeRepository.findById(Grade.initialTesterGrade())
                        .orElseThrow(
                                () -> new GradeNotFoundException("초기 설정용 Grade가 등록되어 있지 않습니다.")
                        ).toDomain()
        );

        return testerRepository.save(TesterEntity.from(tester)).toDomain().getId();

    }
}

