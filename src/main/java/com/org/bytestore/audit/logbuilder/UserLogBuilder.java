package com.org.bytestore.audit.logbuilder;

import com.org.bytestore.audit.AuditLogBuilder;
import com.org.bytestore.enums.OperationEntity;
import com.org.bytestore.enums.OperationType;
import com.org.bytestore.mapper.request.AuditLogRequestToAuditMapper;
import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.AddLogRequest;
import com.org.bytestore.model.request.AddUserRequest;
import com.org.bytestore.model.request.DeleteUserRequest;
import com.org.bytestore.model.request.UpdateUserInfoRequest;
import com.org.bytestore.repository.AuditRepository;
import com.org.bytestore.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserLogBuilder extends AuditLogBuilder {

    private final UserRepository userRepo;

    public UserLogBuilder(final UserRepository userRepo, final AuditRepository auditRepository,
                          final AuditLogRequestToAuditMapper mapper) {
        super(auditRepository, mapper);
        this.userRepo = userRepo;
    }

    public void buildAndPublish(final AddUserRequest addUserRequest) {
        final Optional<User> requesterInfo = userRepo.findById(addUserRequest.getPrincipleUserId());
        if(requesterInfo.isPresent()) {
            final AddLogRequest logRequest = AddLogRequest.builder()
                    .orgTokenId(addUserRequest.getOrgTokenId())
                    .principleUserId(addUserRequest.getPrincipleUserId())
                    .userName(requesterInfo.get().getUserDetails().getUserName())
                    .operationType(OperationType.CREATE)
                    .operationEntity(OperationEntity.USER)
                    .changes(addUserRequest.getUserDetails().getUserName())
                    .build();
            addLog(logRequest);
        }
    }

    public void buildAndPublish(final DeleteUserRequest deleteUserRequest) {
        final Optional<User> requesterInfo = userRepo.findById(deleteUserRequest.getPrincipleUserId());
        if(requesterInfo.isPresent()) {
            final AddLogRequest logRequest = AddLogRequest.builder()
                    .orgTokenId(deleteUserRequest.getOrgTokenId())
                    .principleUserId(deleteUserRequest.getPrincipleUserId())
                    .userName(requesterInfo.get().getUserDetails().getUserName())
                    .changes(deleteUserRequest.getUserNameToBeDeleted())
                    .operationEntity(OperationEntity.USER)
                    .operationType(OperationType.DELETE)
                    .build();
            addLog(logRequest);
        }
    }

    public void buildAndPublish(final User signedUpUser) {
        final AddLogRequest logRequest =  AddLogRequest.builder()
                .orgTokenId(signedUpUser.getOrgTokenId())
                .principleUserId(signedUpUser.getUserId())
                .userName(signedUpUser.getUserDetails().getUserName())
                .operationType(OperationType.CREATE)
                .operationEntity(OperationEntity.USER)
                .changes("Themself")
                .build();
        addLog(logRequest);
    }

    public void buildAndPublish (final UpdateUserInfoRequest updateRequest){
        final Optional<User> requesterInfo = userRepo.findById(updateRequest.getUserId());
        if(requesterInfo.isPresent()){
            final AddLogRequest addLogRequest = AddLogRequest.builder()
                    .operationType(OperationType.UPDATE)
                    .operationEntity(OperationEntity.USER)
                    .userName(updateRequest.getUserDetails().getUserName())
                    .orgTokenId(updateRequest.getOrgTokenId())
                    .principleUserId(updateRequest.getUserId())
                    .changes("Themself")
                    .build();
            addLog(addLogRequest);
        }
    }
}
