package io.wisoft.testermatchingplatform.service.tester;

import io.wisoft.testermatchingplatform.domain.category.Category;
import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.grade.Grade;
import io.wisoft.testermatchingplatform.domain.grade.GradeRepository;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import io.wisoft.testermatchingplatform.domain.tester.TesterRepository;
import io.wisoft.testermatchingplatform.handler.exception.CategoryNotFoundException;
import io.wisoft.testermatchingplatform.handler.exception.EmailOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.NicknameOverlapException;
import io.wisoft.testermatchingplatform.web.dto.request.TesterRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TesterRegisterService {

    private final TesterRepository testerRepository;
    private final CategoryRepository categoryRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public TesterRegisterService(
            final TesterRepository testerRepository,
            final CategoryRepository categoryRepository,
            final GradeRepository gradeRepository) {
        this.testerRepository = testerRepository;
        this.categoryRepository = categoryRepository;
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public Long registerTester(TesterRegisterRequest testerRequest) {

        // 예외 처리 하는 방법 필요
        // 동일 아이디 있으면 예외처리
        // 동일 닉네임 있으면 예외처리
        if(testerRepository.findByEmail(testerRequest.getEmail()).isPresent()) {
            throw new EmailOverlapException(testerRequest.getEmail() + "은 이미 가입된 이메일입니다.");
        } else if (testerRepository.findByNickname(testerRequest.getNickname()).isPresent()){
            throw new NicknameOverlapException(testerRequest.getNickname() + "은 이미 가입된 닉네임입니다.");
        }

        // 선택한 카테고리가 목록에 없음
        Category categoryDomain = categoryRepository.findByName(testerRequest.getPreferCategoryName())
                .orElseThrow(
                        () -> new CategoryNotFoundException(testerRequest.getPreferCategoryName() + "인 카테고리가 없음")
                ).toDomain();
        // 선택한 등급을 알기 쉽도록 enum 사용
        Grade gradeDomain = gradeRepository.findByName("Level1").toDomain();

        Tester tester = new Tester(
                testerRequest.getEmail(),
                testerRequest.getPassword(),
                testerRequest.getNickname(),
                testerRequest.getPhoneNumber(),
                categoryDomain,
                testerRequest.getIntroMessage(),
                testerRequest.getIntroPictureReference(),
                gradeDomain
        );

        return testerRepository.save(TesterEntity.from(tester)).toDomain().getId();

    }

}
