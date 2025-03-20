package com.org.bytestore.audit.logbuilder;

import com.org.bytestore.audit.AuditLogBuilder;
import com.org.bytestore.enums.OperationEntity;
import com.org.bytestore.enums.OperationType;
import com.org.bytestore.mapper.request.AuditLogRequestToAuditMapper;
import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.AddLogRequest;
import com.org.bytestore.model.request.UpdateOrganisationRequest;
import com.org.bytestore.repository.AuditRepository;
import com.org.bytestore.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrganisationLogBuilder extends AuditLogBuilder {
    private final UserRepository userRepo;

    public OrganisationLogBuilder(final AuditRepository auditRepository,
                                  final AuditLogRequestToAuditMapper mapper, final UserRepository userRepo) {
        super(auditRepository,mapper);
        this.userRepo = userRepo;
    }

    public void buildAndPublish (final UpdateOrganisationRequest updateRequest){
        final Optional<User> requesterInfo = userRepo.findById(updateRequest.getUserId());
        if(requesterInfo.isPresent()){
            final AddLogRequest logRequest = AddLogRequest.builder()
                    .operationType(OperationType.UPDATE)
                    .operationEntity(OperationEntity.ORG)
                    .orgTokenId(updateRequest.getOrganisation().getOrgTokenId())
                    .principleUserId(updateRequest.getUserId())
                    .userName(requesterInfo.get().getUserDetails().getUserName())
                    .changes("")
                    .build();
            addLog(logRequest);
        }
    }
}
