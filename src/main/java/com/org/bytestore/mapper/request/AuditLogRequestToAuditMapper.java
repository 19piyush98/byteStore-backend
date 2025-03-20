package com.org.bytestore.mapper.request;

import com.org.bytestore.model.entity.Audit;
import com.org.bytestore.model.request.AddLogRequest;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class AuditLogRequestToAuditMapper {

    public Audit map(final AddLogRequest addLogRequest) {
        return Audit.builder()
                .logId(UUID.randomUUID().toString())
                .orgTokenId(addLogRequest.getOrgTokenId())
                .userName(addLogRequest.getUserName())
                .principleUserId(addLogRequest.getPrincipleUserId())
                .operationType(addLogRequest.getOperationType())
                .operationEntity(addLogRequest.getOperationEntity())
                .changes(addLogRequest.getChanges())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }
}
