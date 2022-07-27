package io.wisoft.testermatchingplatform.service.tester;

import io.wisoft.testermatchingplatform.domain.category.CategoryRepository;
import io.wisoft.testermatchingplatform.domain.tester.Tester;
import io.wisoft.testermatchingplatform.domain.tester.TesterRepository;
import io.wisoft.testermatchingplatform.handler.FileHandler;
import io.wisoft.testermatchingplatform.handler.exception.auth.EmailOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.auth.NicknameOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.category.CategoryNotFoundException;
import io.wisoft.testermatchingplatform.handler.exception.tester.TesterAuthException;
import io.wisoft.testermatchingplatform.handler.exception.tester.TesterNotFoundException;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterSignInRequest;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.resp.tester.TesterSignInResponse;
import io.wisoft.testermatchingplatform.web.dto.resp.tester.TesterUpdateResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TesterAuthService {

    private final TesterRepository testerRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public TesterSignInResponse loginTester(TesterSignInRequest request) {
        if (!testerRepository.existsByEmailAndPassword(request.getEmail(), request.getPassword())) {
            throw new TesterAuthException("email, password 가 일치하지 않습니다.");
        }

        TesterSignInResponse response =
                new TesterSignInResponse(testerRepository.findByEmail(request.getEmail()).orElseThrow().getId());

        return response;
    }

    @Transactional
    public TesterUpdateResponse updateTester(TesterUpdateRequest testerUpdateRequest, Long testerId) {

        Tester tester = testerRepository.findById(testerId).orElseThrow(
                () -> new TesterNotFoundException("tester ID: " + testerId + "를 찾을 수 없습니다."));

        if (testerRepository.existsByEmail(testerUpdateRequest.getEmail())) {
            throw new EmailOverlapException(testerUpdateRequest.getEmail() + "은 중복입니다.");
        }
        if (testerRepository.existsByNickname(testerUpdateRequest.getNickname())) {
            throw new NicknameOverlapException(testerUpdateRequest.getNickname() + "은 중복입니다.");
        }


        tester.setId(testerId);
        tester.setEmail(testerUpdateRequest.getEmail());
        tester.setPassword(testerUpdateRequest.getPassword());
        tester.setPhoneNumber(testerUpdateRequest.getPhoneNumber());
        tester.setPreferCategory(
                categoryRepository.findById(testerUpdateRequest.getPreferCategoryId())
                        .orElseThrow(
                                () -> new CategoryNotFoundException("Category Id: " + testerUpdateRequest.getPreferCategoryId() + "를 찾을 수 없음")
                        )
        );
        tester.setIntroMessage(testerUpdateRequest.getIntroMessage());
        if (!testerUpdateRequest.getIntroPicture().isEmpty()) {

//            이전 사진을 제거하고 새로운 사진을 저장할 수 있도록 하는 로직 필요
            String profilePath = FileHandler.saveFileData(testerUpdateRequest.getIntroPicture());
            tester.setIntroPictureRef(profilePath);

        }

        TesterUpdateResponse response =
                new TesterUpdateResponse(testerRepository.save(tester).getId());

        return response;
    }


}
