package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.service.TesterManageService;
import io.wisoft.testermatchingplatform.service.register.TesterRegisterService;
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

    @GetMapping("testers")
    public DetailTesterResponse getTesterInformation(@RequestParam("tester_id") Long testerId) {
        return testerManageService.findByTesterId(testerId);
    }

    @PatchMapping("testers")
    public Long updateTester(@RequestBody TesterUpdateRequest testerUpdateRequest) {
        return testerManageService.updateTester(testerUpdateRequest);
    }






}
