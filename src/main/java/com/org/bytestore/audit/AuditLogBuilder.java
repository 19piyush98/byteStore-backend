package com.org.bytestore.audit;

import com.org.bytestore.mapper.request.AuditLogRequestToAuditMapper;
import com.org.bytestore.model.entity.Audit;
import com.org.bytestore.model.request.AddLogRequest;
import com.org.bytestore.repository.AuditRepository;
import com.org.bytestore.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public abstract class AuditLogBuilder {

    private final AuditRepository auditRepository;

    private final AuditLogRequestToAuditMapper mapper;

    public void addLog(final AddLogRequest addLogRequest) {
        log.info("Adding audit log {}", GsonUtils.toJson(addLogRequest));
        try {
            final Audit audit = mapper.map(addLogRequest);
            auditRepository.save(audit);
            log.info("Logs added successfully...");
        } catch (final Exception e) {
            log.error("Failed to add audit log...", e);
        }
    }
}
