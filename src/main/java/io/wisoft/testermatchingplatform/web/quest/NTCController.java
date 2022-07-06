package io.wisoft.testermatchingplatform.web.quest;

import io.wisoft.testermatchingplatform.service.register.NTCRegisterService;
import io.wisoft.testermatchingplatform.web.dto.request.NTCRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NTCController {

    final NTCRegisterService ntcRegisterService;

    @Autowired
    public NTCController(NTCRegisterService ntcRegisterService) {
        this.ntcRegisterService = ntcRegisterService;
    }

    @PostMapping("/ntcs")
    public Long registerNTC(@RequestBody NTCRegisterRequest ntcRegisterRequest) {
        return ntcRegisterService.registerNTC(ntcRegisterRequest);
    }
}
