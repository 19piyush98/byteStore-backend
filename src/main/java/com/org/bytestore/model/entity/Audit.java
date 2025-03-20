package com.org.bytestore.model.entity;

import com.org.bytestore.enums.OperationEntity;
import com.org.bytestore.enums.OperationType;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document("Audit")
@Data
public class Audit {

    @Id
    private String logId;

    @NonNull
    private String orgTokenId;

    private String principleUserId;

    private String userName;

    private OperationType operationType;

    private OperationEntity operationEntity;

    private String changes;

    private Long timestamp;
}
