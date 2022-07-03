package io.wisoft.testermatchingplatform.service.register;

import io.wisoft.testermatchingplatform.domain.tester.TesterRepository;
import io.wisoft.testermatchingplatform.web.dto.request.TesterRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TesterRegisterService {

    private final TesterRepository testerRepository;

    @Autowired
    public TesterRegisterService(final TesterRepository testerRepository) {
        this.testerRepository = testerRepository;
    }

    @Transactional
    public void registerTester(TesterRegisterRequest testerRequest) {

        // 예외 처리 하는 방법 필요
        // 동일 아이디 있으면 예외처리
        // 동일 닉네임 있으면 예외처리
//        if(testerRepository.findByEmail(testerRequest.getEmail()).isPresent())

    }

}
