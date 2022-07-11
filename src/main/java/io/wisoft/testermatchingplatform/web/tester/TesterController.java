package io.wisoft.testermatchingplatform.web.tester;

import io.wisoft.testermatchingplatform.handler.validator.image.ValidationSequence;
import io.wisoft.testermatchingplatform.service.tester.TesterManageService;
import io.wisoft.testermatchingplatform.service.tester.TesterRegisterService;
import io.wisoft.testermatchingplatform.web.dto.request.TesterLoginRequest;
import io.wisoft.testermatchingplatform.web.dto.request.TesterUpdateRequest;
import io.wisoft.testermatchingplatform.web.dto.response.DetailTesterResponse;
import io.wisoft.testermatchingplatform.web.dto.request.TesterRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity<Long> registerTester(@RequestBody @Validated(ValidationSequence.class) final TesterRegisterRequest testerRegisterRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(testerRegisterService.registerTester(testerRegisterRequest));

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
            @RequestBody @Valid final TesterLoginRequest testerLoginRequest) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(testerManageService.loginTester(testerLoginRequest));
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
