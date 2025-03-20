package com.org.bytestore.service;

import com.org.bytestore.mapper.response.AuditToGetLogResponseMapper;
import com.org.bytestore.model.entity.Audit;
import com.org.bytestore.model.request.GetLogRequest;
import com.org.bytestore.model.response.GetLogResponse;
import com.org.bytestore.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuditService {

    private final AuditRepository auditRepo;

    private final AuditToGetLogResponseMapper auditToGetLogResponseMapper;

    public Optional<GetLogResponse> getLogs(@RequestBody GetLogRequest request) {
        Optional<List<Audit>> audits = auditRepo.findByOrgTokenId(request.getOrgTokenId());
        if (audits.isPresent()) {
            final GetLogResponse response = auditToGetLogResponseMapper.map(audits.get());
            return Optional.of(response);
        }
        return Optional.of(GetLogResponse.builder().logs(List.of()).build());
    }
}
