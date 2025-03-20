package com.org.bytestore.model.request;

import com.org.bytestore.enums.OperationEntity;
import com.org.bytestore.enums.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Builder
@Getter
public class AddLogRequest {

    @NonNull
    private String orgTokenId;

    private String principleUserId;

    private String userName;

    private OperationType operationType;

    private OperationEntity operationEntity;

    private String changes;
}
