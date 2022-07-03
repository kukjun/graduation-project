package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.service.register.TesterRegisterService;
import io.wisoft.testermatchingplatform.web.dto.request.TesterRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesterController {

    final TesterRegisterService testerRegisterService;

    @Autowired
    TesterController(TesterRegisterService testerRegisterService) {
        this.testerRegisterService = testerRegisterService;
    }

    @PostMapping("testers")
    public Long RegisterTester(@RequestBody TesterRegisterRequest testerRegisterRequest) {
        return testerRegisterService.registerTester(testerRegisterRequest);
    }

}
