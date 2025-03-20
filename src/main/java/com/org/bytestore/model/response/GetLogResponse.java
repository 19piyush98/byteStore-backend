package com.org.bytestore.model.response;

import com.org.bytestore.enums.OperationEntity;
import com.org.bytestore.enums.OperationType;
import com.org.bytestore.model.entity.Audit;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GetLogResponse {

    private String message;

    private Boolean success;

    private List<Log> logs;

    @Builder
    @Getter
    public static class Log {
        private String logId;

        private String logMessage;

        private Long timestamp;
    }
}
