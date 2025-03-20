package com.org.bytestore.audit.logbuilder;

import com.org.bytestore.audit.AuditLogBuilder;
import com.org.bytestore.enums.OperationEntity;
import com.org.bytestore.enums.OperationType;
import com.org.bytestore.mapper.request.AuditLogRequestToAuditMapper;
import com.org.bytestore.model.entity.User;
import com.org.bytestore.model.request.AddLogRequest;
import com.org.bytestore.model.request.DeleteProductRequest;
import com.org.bytestore.model.request.ProductModifyRequest;
import com.org.bytestore.model.request.UpdateProductRequest;
import com.org.bytestore.repository.AuditRepository;
import com.org.bytestore.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductLogBuilder extends AuditLogBuilder {

    private final UserRepository userRepo;

    public ProductLogBuilder(final UserRepository userRepo, final AuditRepository auditRepository,
                             final AuditLogRequestToAuditMapper mapper) {
        super(auditRepository, mapper);
        this.userRepo = userRepo;
    }

    public void buildAndPublish(final ProductModifyRequest request) {
        final Optional<User> requester = userRepo.findById(request.getUserId());
        if (requester.isPresent()) {
            final AddLogRequest logRequest = AddLogRequest
                    .builder()
                    .orgTokenId(request.getOrgTokenId())
                    .principleUserId(request.getUserId())
                    .userName(requester.get().getUserDetails().getUserName())
                    .operationType(OperationType.CREATE)
                    .operationEntity(OperationEntity.PRODUCT)
                    .changes(request.getProductName())
                    .build();
            addLog(logRequest);
        }
    }

    public void buildAndPublish (final DeleteProductRequest request){
        final Optional<User> requester = userRepo.findById(request.getUserId());
        if(requester.isPresent()){
            final AddLogRequest logRequest = AddLogRequest.builder()
                    .orgTokenId(request.getOrgTokenId())
                    .principleUserId(request.getUserId())
                    .userName(requester.get().getUserDetails().getUserName())
                    .operationType(OperationType.DELETE)
                    .operationEntity(OperationEntity.PRODUCT)
                    .changes(request.getProductNameToBeDeleted())
                    .build();
            addLog(logRequest);
        }
    }

    public void buildAndPublish (final UpdateProductRequest request){
        final Optional<User> requester = userRepo.findById(request.getProductModifyRequest().getUserId());
        if(requester.isPresent()){
            final AddLogRequest logRequest = AddLogRequest.builder()
                    .orgTokenId(request.getProductModifyRequest().getOrgTokenId())
                    .principleUserId(request.getProductModifyRequest().getUserId())
                    .userName(requester.get().getUserDetails().getUserName())
                    .operationEntity(OperationEntity.PRODUCT)
                    .operationType(OperationType.UPDATE)
                    .changes(request.getProductModifyRequest().getProductName())
                    .build();
            addLog(logRequest);
        }
    }
}
