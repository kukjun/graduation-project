package io.wisoft.testermatchingplatform.service.register;

import io.wisoft.testermatchingplatform.domain.ntc.NTCRepository;
import io.wisoft.testermatchingplatform.handler.exception.EmailOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.NicknameOverlapException;
import io.wisoft.testermatchingplatform.web.dto.request.NTCRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NTCRegisterService {

    private final NTCRepository ntcRepository;

    @Transactional
    public Long registerNTC(NTCRegisterRequest ntcRequest){

        // 예외 처리 하는 방법 공부 필요
      if (ntcRepository.findByEmail(ntcRequest.getEmail()).isPresent()) {
            throw new EmailOverlapException(ntcRequest.getEmail() + "은 이미 가입된 이메일입니다.");
        }
        else if (ntcRepository.findByNickname(ntcRequest.getEmail()).isPresent()) {
            throw new NicknameOverlapException(ntcRequest.getNickname() + "은 이미 기입된 닉네임입니다.");
        }

        return ntcRepository.save(ntcRequest.toEntity()).toDomain().getId();

    }
}