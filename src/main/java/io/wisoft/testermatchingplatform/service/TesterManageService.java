package io.wisoft.testermatchingplatform.service;

import io.wisoft.testermatchingplatform.web.dto.request.TesterLoginRequest;
import io.wisoft.testermatchingplatform.web.dto.request.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.response.DetailTesterResponse;
import org.springframework.stereotype.Service;

@Service
public class TesterManageService {

    public void deleteTester(Long testerId) {
    }

    public DetailTesterResponse findByTesterId(Long testerId) {
        return null;
    }

    public Long loginTester(TesterLoginRequest testerLoginRequest) {
        return 0L;
    }

    public Long updateTester(TesterUpdateRequest testerUpdateRequest) {
        return 0L;
    }
}
