package io.wisoft.testermatchingplatform.web.controller.tester;

import io.wisoft.testermatchingplatform.handler.validator.image.ValidationSequence;
import io.wisoft.testermatchingplatform.service.tester.TesterManageService;
import io.wisoft.testermatchingplatform.service.tester.TesterAuthService;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterSignInRequest;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterSignUpRequest;
import io.wisoft.testermatchingplatform.web.dto.req.tester.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.resp.tester.DetailTesterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TesterController {

    final TesterAuthService testerAuthService;
    final TesterManageService testerManageService;

    @Autowired
    TesterController(
            TesterAuthService testerAuthService,
            TesterManageService testerManageService
    ) {
        this.testerAuthService = testerAuthService;
        this.testerManageService = testerManageService;
    }

    @PostMapping("testers")
    public ResponseEntity<Long> registerTester(@RequestBody @Validated(ValidationSequence.class) final TesterSignUpRequest testerSignUpRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(testerManageService.registerTester(testerSignUpRequest));

    }

//    연관 내용 전부 지워야 함.
//    @DeleteMapping("testers/{tester_id}")
//    public ResponseEntity deleteTester(@PathVariable("tester_id") Long testerId) {
//        testerManageService.deleteTester(testerId);
//        return ResponseEntity
//                .noContent().build();
//    }

    @PostMapping("testers/login")
    public ResponseEntity<Long> loginTester(
            @RequestBody @Valid final TesterSignInRequest testerSignInRequest) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(testerAuthService.loginTester(testerSignInRequest));
    }

    @GetMapping("testers/{tester_id}")
    public DetailTesterResponse getTesterInformation(@PathVariable("tester_id") Long testerId) {
        return testerManageService.findByTesterId(testerId);
    }

//    @PatchMapping("testers/{tester_id}")
//    public Long updateTester(@PathVariable("tester_id") TesterUpdateRequest testerUpdateRequest) {
//        return testerManageService.updateTester(testerUpdateRequest);
//    }


}
