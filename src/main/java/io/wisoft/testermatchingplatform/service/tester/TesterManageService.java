package io.wisoft.testermatchingplatform.service.tester;

import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import io.wisoft.testermatchingplatform.domain.tester.TesterRepository;
import io.wisoft.testermatchingplatform.handler.FileHandler;
import io.wisoft.testermatchingplatform.handler.exception.auth.EmailNotEqualException;
import io.wisoft.testermatchingplatform.handler.exception.auth.EmailOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.auth.NicknameOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.auth.PasswordNotEqualException;
import io.wisoft.testermatchingplatform.handler.exception.category.CategoryNotFoundException;
import io.wisoft.testermatchingplatform.handler.exception.tester.TesterNotFoundException;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterLoginRequest;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.resp.tester.DetailTesterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TesterManageService {
    private final TesterRepository testerRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TesterManageService(final TesterRepository testerRepository, final CategoryRepository categoryRepository) {
        this.testerRepository = testerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void deleteTester(Long testerId) {
        if (testerRepository.existsById(testerId)) {
            testerRepository.deleteById(testerId);
        } else {
            throw new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다.");
        }
    }

    @Transactional(readOnly = true)
    public DetailTesterResponse findByTesterId(Long testerId) {

        Tester tester = testerRepository.findById(testerId)
                .orElseThrow(() -> new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다."))
                .toDomain();

        return new DetailTesterResponse(tester);
    }

    @Transactional(readOnly = true)
    public Long loginTester(TesterLoginRequest testerLoginRequest) {
        Tester findTester = testerRepository.findByEmail(testerLoginRequest.getEmail())
                .orElseThrow(() -> new EmailNotEqualException("이메일이 맞지 않습니다."))
                .toDomain();

        // Domain Logic
        if (findTester.getPassword().equals(testerLoginRequest.getPassword())) {
            return findTester.getId();
        } else {
            throw new PasswordNotEqualException("비밀번호가 틀립니다.");
        }

    }

    @Transactional
    public Long updateTester(TesterUpdateRequest testerUpdateRequest) {

        Tester tester = testerRepository.findById(testerUpdateRequest.getId()).orElseThrow(
                        () -> new TesterNotFoundException("tester ID: " + testerUpdateRequest.getId() + "를 찾을 수 없습니다."))
                .toDomain();

        if (testerRepository.findByEmail(testerUpdateRequest.getEmail()).isPresent()) {
            throw new EmailOverlapException(testerUpdateRequest.getEmail() + "은 중복입니다.");
        }
        tester.setEmail(testerUpdateRequest.getEmail());
        tester.setPassword(testerUpdateRequest.getPassword());
        if (testerRepository.findByNickname(testerUpdateRequest.getNickname()).isPresent()) {
            throw new NicknameOverlapException(testerUpdateRequest.getNickname() + "은 중복입니다.");
        }
        tester.setPhoneNumber(testerUpdateRequest.getPhoneNumber());
        tester.setPreferCategory(
                categoryRepository.findByName(testerUpdateRequest.getPreferCategoryName())
                        .orElseThrow(
                                () -> new CategoryNotFoundException(testerUpdateRequest.getPreferCategoryName() + "를 찾을 수 없음")
                        ).toDomain()
        );
            tester.setIntroMessage(testerUpdateRequest.getIntroMessage());
        if (!testerUpdateRequest.getIntroPicture().isEmpty()) {
//            이전 사진을 제거하고 새로운 사진을 저장할 수 있도록 하는 로직 필요
            String profilePath = FileHandler.saveFileData(testerUpdateRequest.getIntroPicture());
            tester.setIntroPictureReference(profilePath);
        }

        return testerRepository.save(TesterEntity.from(tester)).toDomain().getId();
    }
}
