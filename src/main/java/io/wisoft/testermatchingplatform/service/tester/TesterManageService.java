package io.wisoft.testermatchingplatform.service.tester;

import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import io.wisoft.testermatchingplatform.domain.tester.TesterEntity;
import io.wisoft.testermatchingplatform.domain.tester.TesterRepository;
import io.wisoft.testermatchingplatform.handler.exception.*;
import io.wisoft.testermatchingplatform.web.dto.request.TesterLoginRequest;
import io.wisoft.testermatchingplatform.web.dto.request.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.response.DetailTesterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesterManageService {
    private TesterRepository testerRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public TesterManageService(final TesterRepository testerRepository, final CategoryRepository categoryRepository) {
        this.testerRepository = testerRepository;
        this.categoryRepository = categoryRepository;
    }

    public void deleteTester(Long testerId) {
        // 조회
        // 있으면 지우기
        // 없으면 예외
        if (testerRepository.existsById(testerId)) {
            testerRepository.deleteById(testerId);
        } else {
            throw new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다.");
        }
    }

    public DetailTesterResponse findByTesterId(Long testerId) {

        Tester tester = testerRepository.findById(testerId)
                .orElseThrow(() -> new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다."))
                .toDomain();

        return new DetailTesterResponse(tester);
    }

    public Long loginTester(TesterLoginRequest testerLoginRequest) {
        Tester findTester = testerRepository.findByEmail(testerLoginRequest.getEmail())
                .orElseThrow(() -> new EmailNotEqualException("이메일이 맞지 않습니다."))
                .toDomain();

        if (findTester.getPassword().equals(testerLoginRequest.getPassword())) {
            return findTester.getId();
        }
        else {
            throw new PasswordNotEqualException("비밀번호가 틀립니다.");
        }


    }

    public Long updateTester(TesterUpdateRequest testerUpdateRequest) {

        TesterEntity testerEntity = testerRepository.findById(testerUpdateRequest.getId()).orElseThrow(
                () -> new TesterNotFoundException("tester ID: " + testerUpdateRequest.getId() + "를 찾을 수 없습니다."));

        Tester tester = testerEntity.toDomain();


        if (!testerUpdateRequest.getEmail().isBlank()) {
            // 중복 검사
            if (testerRepository.findByEmail(testerUpdateRequest.getEmail()).isPresent()) {
                throw new EmailOverlapException(testerUpdateRequest.getEmail() + "은 중복입니다.");
            }
            tester.setEmail(testerUpdateRequest.getEmail());
        }
        if (!testerUpdateRequest.getPassword().isBlank()) {
            tester.setPassword(testerUpdateRequest.getPassword());
        }
        if (!testerUpdateRequest.getNickname().isBlank()) {
            // 중복 검사
            if (testerRepository.findByNickname(testerUpdateRequest.getNickname()).isPresent()) {
                throw new NicknameOverlapException(testerUpdateRequest.getNickname() + "은 중복입니다.");
            }
        }
        if (!testerUpdateRequest.getPhoneNumber().isBlank()) {
            tester.setPhoneNumber(testerUpdateRequest.getPhoneNumber());
        }
        if (!testerUpdateRequest.getPreferCategory().isBlank()) {
            tester.setPreferCategory(
                    categoryRepository.findByName(testerUpdateRequest.getPreferCategory())
                            .orElseThrow(
                                    () -> new CategoryNotFoundException(testerUpdateRequest.getPreferCategory() + "를 찾을 수 없음")
                            ).toDomain()
            );
        }
        if (!testerUpdateRequest.getIntroMessage().isBlank()) {
            tester.setIntroMessage(testerUpdateRequest.getIntroMessage());
        }
        if (!testerUpdateRequest.getIntroPictrue().isEmpty()) {
//            이전 사진을 제거하거 새로운 사진을 저장할 수 있도록 하는 로직 필요
        }


        testerEntity.SynchronizeFromDomain(tester);



        return 0L;
    }
}
