package io.wisoft.testermatchingplatform.web.tester;

import io.wisoft.testermatchingplatform.service.tester.TesterManageService;
import io.wisoft.testermatchingplatform.service.tester.TesterRegisterService;
import io.wisoft.testermatchingplatform.web.dto.request.TesterLoginRequest;
import io.wisoft.testermatchingplatform.web.dto.request.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.response.DetailTesterResponse;
import io.wisoft.testermatchingplatform.web.dto.request.TesterRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TesterController {

    final TesterRegisterService testerRegisterService;
    final TesterManageService testerManageService;

    @Autowired
    TesterController(
            TesterRegisterService testerRegisterService,
            TesterManageService testerManageService
    ) {
        this.testerRegisterService = testerRegisterService;
        this.testerManageService = testerManageService;
    }

    @PostMapping("testers")
    public Long registerTester(@RequestBody TesterRegisterRequest testerRegisterRequest) {
        return testerRegisterService.registerTester(testerRegisterRequest);
    }

    @DeleteMapping("testers/{tester_id}")
    public void deleteTester(@PathVariable("tester_id") Long testerId) {
        testerManageService.deleteTester(testerId);

    }

    @PostMapping("testers/login")
    public Long loginTester(@RequestBody TesterLoginRequest testerLoginRequest) {
        return testerManageService.loginTester(testerLoginRequest);
    }

    @GetMapping("testers/{tester_id}")
    public DetailTesterResponse getTesterInformation(@PathVariable("tester_id") Long testerId) {
        return testerManageService.findByTesterId(testerId);
    }

    @PatchMapping("testers/{tester_id}")
    public Long updateTester(@PathVariable("tester_id") TesterUpdateRequest testerUpdateRequest) {
        return testerManageService.updateTester(testerUpdateRequest);
    }


}
